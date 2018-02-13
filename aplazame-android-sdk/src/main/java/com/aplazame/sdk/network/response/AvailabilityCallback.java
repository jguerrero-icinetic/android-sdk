package com.aplazame.sdk.network.response;

public interface AvailabilityCallback {

    void onAvailable();

    void onNotAvailable();

    void onFailure();
}
