package com.aplazame.sdk;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.aplazame.sdk.model.Checkout;
import com.aplazame.sdk.network.api.AplazameApiManager;
import com.aplazame.sdk.network.js_interface.JsInterface;
import com.aplazame.sdk.network.js_interface.JsWebViewEvents;
import com.aplazame.sdk.network.response.AvailabilityCallback;
import com.aplazame.sdk.network.utils.ImageUtils;

import java.io.File;
import java.io.IOException;

public class AplazameSDK {

    private static final String TAG = AplazameSDK.class.getSimpleName();

    private static AplazameApiManager aplazameApiManager;
    // To avoid using parcelables
    private static Checkout checkout;

    private static String imagePath;
    private static ValueCallback<Uri> uriCallback;
    private static ValueCallback<Uri[]> urisCallback;
    private static ValueCallback<Uri[]> filePathCallback;
    private final static int REQUEST_CODE_FILE_CHOOSER = 4441;
    private final static int REQUEST_CODE_PERMISSIONS = 4442;
    private static Activity activity;

    public static void setConfiguration(String token, Boolean debug) {
        aplazameApiManager = new AplazameApiManager(token, debug);
    }

    public static void checkAvailability(Double amount, String currency, AvailabilityCallback responseCallback) {
        checkAplazameSdkConfiguration();
        aplazameApiManager.checkAvailability(amount, currency, responseCallback);
    }

    public static String initializeCheckoutUrl() {
        checkAplazameSdkConfiguration();
        checkCheckout();
        return aplazameApiManager.initializeCheckoutUrl();
    }

    public static void initializeAplazameWebView(final Activity act, final WebView webView, final JsWebViewEvents events) {
        activity = act;
        checkAplazameSdkConfiguration();
        checkCheckout();
        checkWebView(webView);
        webView.loadUrl(AplazameSDK.initializeCheckoutUrl());
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            webSettings.setMixedContentMode(0);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        webView.addJavascriptInterface(new JsInterface(events, new JsInterface.OnSendPostMessageAgain() {
            @Override
            public void sendPostMessageAgain() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.evaluateJavascript(aplazameApiManager.requestCheckout(checkout), null);
                    }
                });
            }
        }), aplazameApiManager.getAndroidJsInterfaceName());

        WebView.setWebContentsDebuggingEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                events.onPageStarted();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                events.onPageFinished();
                webView.evaluateJavascript(aplazameApiManager.requestCheckout(checkout), null);
                webView.evaluateJavascript(aplazameApiManager.addEventListener(), null);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            //For Android 4.1+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                uriCallback = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                activity.startActivityForResult(Intent.createChooser(i, "File Chooser"), REQUEST_CODE_FILE_CHOOSER);
            }
            //For Android 5.0+
            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePath,
                    WebChromeClient.FileChooserParams fileChooserParams) {
                filePathCallback = filePath;

                if(Build.VERSION.SDK_INT >=23 && (ContextCompat.checkSelfPermission
                        (activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            REQUEST_CODE_PERMISSIONS);
                } else {
                    uploadImage();
                }

                return true;
            }
        });
    }

    private static void uploadImage() {
        if (urisCallback != null) {
            urisCallback.onReceiveValue(null);
        }
        urisCallback = filePathCallback;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = new ImageUtils().createImageFile();
                takePictureIntent.putExtra("PhotoPath", imagePath);
            } catch(IOException ex) {
                Log.e(TAG, "Image file creation failed", ex);
            }
            if (photoFile != null) {
                imagePath = "file:" + photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            } else {
                takePictureIntent = null;
            }
        }

        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");
        Intent intentArray[];

        if (takePictureIntent != null) {
            intentArray = new Intent[] {takePictureIntent};
        } else {
            intentArray = new Intent[0];
        }

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

        activity.startActivityForResult(chooserIntent, REQUEST_CODE_FILE_CHOOSER);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent intent) {
        final int RESULT_OK = -1;

        if (Build.VERSION.SDK_INT >= 21) {
            Uri[] results = null;

            if (resultCode== Activity.RESULT_OK) {
                if (requestCode == REQUEST_CODE_FILE_CHOOSER) {
                    if (urisCallback == null) {
                        return;
                    }
                    if (intent == null || intent.getData() == null) {
                        //Capture Photo if no image available
                        if (imagePath != null) {
                            results = new Uri[] {Uri.parse(imagePath)};
                        }
                    } else {
                        String dataString = intent.getDataString();
                        if (dataString != null) {
                            results = new Uri[] {Uri.parse(dataString)};
                        }
                    }
                }
            }
            try {
                urisCallback.onReceiveValue(results);
            } catch (IllegalStateException e) {
            } finally {
                urisCallback = null;
            }
        } else {
            if (requestCode == REQUEST_CODE_FILE_CHOOSER) {
                if (null == uriCallback) {
                    return;
                }

                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();

                try {
                    uriCallback.onReceiveValue(result);
                } catch (IllegalStateException e) {
                } finally {
                    uriCallback = null;
                }
            }
        }
    }

    public static void setCheckout(Checkout checkout) {
        AplazameSDK.checkout = checkout;
    }

    private static void checkAplazameSdkConfiguration() {
        if (aplazameApiManager == null) {
            activity.finish();
        }
    }

    private static void checkCheckout() {
        if (checkout == null) {
            throw new IllegalStateException("You must set the checkout first");
        }
    }

    private static void checkWebView(WebView webView) {
        if (webView == null) {
            throw new IllegalStateException("WebView can't be null");
        }
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS){
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Ok
                uploadImage();
            } else {
                Toast.makeText(activity, activity.getString(R.string.permission_must_accept), Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode, int[] grantResults, String message) {
        if (requestCode == REQUEST_CODE_PERMISSIONS){
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Ok
                uploadImage();
            } else {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void onRequestPermissionsResult(Activity activity, int requestCode, int[] grantResults, OnRequestPermissionsListener onRequestPermissionsListener) {
        if (requestCode == REQUEST_CODE_PERMISSIONS){
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                uploadImage();
                onRequestPermissionsListener.onAcceptPermissions();
            } else {
                onRequestPermissionsListener.onDeclinePermissions();
            }
        }
    }

    public interface OnRequestPermissionsListener {
        void onAcceptPermissions();

        void onDeclinePermissions();
    }
}
