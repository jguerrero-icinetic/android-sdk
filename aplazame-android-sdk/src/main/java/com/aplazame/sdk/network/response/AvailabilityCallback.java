package com.aplazame.sdk.network.response;

public interface AvailabilityCallback {

    void onAvailabilitySuccess();

    void onAvailabilityFailure();

    void onFailure();
}
