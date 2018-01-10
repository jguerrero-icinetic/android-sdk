package com.aplazame.sdk.network.js_interface;

public interface JsWebViewEvents {

    String SUCCESS = "success";
    String PENDING = "pending";
    String DISMISS = "dismiss";
    String KO = "ko";

    void onPageStarted();

    void onPageFinished();

    void onReadyEvent();

    void onStatusChangeEvent(String status);

    void onCloseEvent(String status);
}
