package com.aplazame.sdk;

import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aplazame.sdk.model.Checkout;
import com.aplazame.sdk.network.api.AplazameApiManager;
import com.aplazame.sdk.network.js_interface.JsInterface;
import com.aplazame.sdk.network.js_interface.JsWebViewEvents;
import com.aplazame.sdk.network.response.AvailabilityCallback;

public class AplazameSDK {

    private static AplazameApiManager aplazameApiManager;
    // To avoid using parcelables
    private static Checkout checkout;

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

    public static void initializeAplazameWebView(final WebView webView, final JsWebViewEvents events) {
        checkAplazameSdkConfiguration();
        checkCheckout();
        checkWebView(webView);
        webView.loadUrl(AplazameSDK.initializeCheckoutUrl());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

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
    }

    public static void setCheckout(Checkout checkout) {
        AplazameSDK.checkout = checkout;
    }

    private static void checkAplazameSdkConfiguration() {
        if (aplazameApiManager == null) {
            throw new IllegalStateException("You must set the Aplazame SDK configuration first");
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
}
