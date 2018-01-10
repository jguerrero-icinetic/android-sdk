package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class CheckoutAvailabilityDto {

    @SerializedName("allowed")
    boolean allowed;

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
