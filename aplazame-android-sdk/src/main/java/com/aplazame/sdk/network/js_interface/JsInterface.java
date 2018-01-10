package com.aplazame.sdk.network.js_interface;

import android.webkit.JavascriptInterface;

public class JsInterface {

    private JsWebViewEvents events;
    private OnSendPostMessageAgain onSendPostMessageAgain;

    public JsInterface(JsWebViewEvents events, OnSendPostMessageAgain onSendPostMessageAgain) {
        this.events = events;
        this.onSendPostMessageAgain = onSendPostMessageAgain;
    }

    @JavascriptInterface
    public void sendReadyEvent() {
        events.onReadyEvent();
    }

    @JavascriptInterface
    public void sendStatusChangeEvent(String status) {
        events.onStatusChangeEvent(status);
    }

    @JavascriptInterface
    public void sendCloseEvent(String status) {
        events.onCloseEvent(status);
    }

    @JavascriptInterface
    public void sendCheckoutData() {
        onSendPostMessageAgain.sendPostMessageAgain();
    }

    public interface OnSendPostMessageAgain {
        void sendPostMessageAgain();
    }
}
