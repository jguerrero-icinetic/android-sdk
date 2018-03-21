package com.aplazame.sdk.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.aplazame.sdk.AplazameSDK;
import com.aplazame.sdk.network.js_interface.JsWebViewEvents;

public class WebViewAplazameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_aplazame);

        final WebView webView = findViewById(R.id.webview);
        final ProgressBar progressBar = findViewById(R.id.progress);

        AplazameSDK.initializeAplazameWebView(this, webView, new JsWebViewEvents() {
            @Override
            public void onPageStarted() {}

            @Override
            public void onPageFinished() {
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReadyEvent() {}

            @Override
            public void onStatusChangeEvent(String status) {
                switch (status) {
                    case SUCCESS:
                        break;
                    case PENDING:
                        break;
                    case KO:
                        break;
                }
            }

            @Override
            public void onCloseEvent(String status) {
                switch (status) {
                    case SUCCESS:
                        break;
                    case PENDING:
                        finish();
                        break;
                    case DISMISS:
                        finish();
                        break;
                    case KO:
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        AplazameSDK.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AplazameSDK.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
