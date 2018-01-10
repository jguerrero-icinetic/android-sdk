package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Merchant;

public class MerchantBuilder {

    private Long timeoutCheckout;
    private Long timeoutExtra;
    private boolean confirmOnCheckout;
    private boolean closeOnSuccess;

    public MerchantBuilder() {}

    public Merchant create() {
        return new Merchant(timeoutCheckout, timeoutExtra, confirmOnCheckout, closeOnSuccess);
    }

    public MerchantBuilder withTimeoutExtra(Long timeoutExtra) {
        this.timeoutExtra = timeoutExtra;
        return this;
    }

    public MerchantBuilder withTimeoutCheckout(Long timeoutCheckout) {
        this.timeoutCheckout = timeoutCheckout;
        return this;
    }

    public MerchantBuilder withConfirmOnCheckout(boolean confirmOnCheckout) {
        this.confirmOnCheckout = confirmOnCheckout;
        return this;
    }

    public MerchantBuilder withCloseOnSuccess(boolean closeOnSuccess) {
        this.closeOnSuccess = closeOnSuccess;
        return this;
    }
}
