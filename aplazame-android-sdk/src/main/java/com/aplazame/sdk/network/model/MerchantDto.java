package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class MerchantDto {

    private final Long timeoutCheckout;
    @SerializedName("confirm_on_checkout")
    private final boolean confirmOnCheckout;

    /*
     *   Optionals
     */

    private final boolean closeOnSuccess;
    @SerializedName("timeout_extra")
    private final Long timeoutExtra;

    public MerchantDto(Long timeoutCheckout, Long timeoutExtra, boolean confirmOnCheckout, boolean closeOnSuccess) {
        this.timeoutCheckout = timeoutCheckout;
        this.timeoutExtra = timeoutExtra;
        this.confirmOnCheckout = confirmOnCheckout;
        this.closeOnSuccess = closeOnSuccess;
    }

    public Long getTimeoutCheckout() {
        return timeoutCheckout;
    }

    public Long getTimeoutExtra() {
        return timeoutExtra;
    }

    public boolean isConfirmOnCheckout() {
        return confirmOnCheckout;
    }

    public boolean isCloseOnSuccess() {
        return closeOnSuccess;
    }
}
