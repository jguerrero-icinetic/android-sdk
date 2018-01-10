package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.MerchantDto;

public class MerchantDtoBuilder {

    private Long timeoutCheckout;
    private Long timeoutExtra;
    private boolean confirmOnCheckout;
    private boolean closeOnSuccess;

    public MerchantDtoBuilder() {}

    public MerchantDto create() {
        return new MerchantDto(timeoutCheckout, timeoutExtra, confirmOnCheckout, closeOnSuccess);
    }

    public MerchantDtoBuilder withTimeoutExtra(Long timeoutExtra) {
        this.timeoutExtra = timeoutExtra;
        return this;
    }

    public MerchantDtoBuilder withTimeoutCheckout(Long timeoutCheckout) {
        this.timeoutCheckout = timeoutCheckout;
        return this;
    }

    public MerchantDtoBuilder withConfirmOnCheckout(boolean confirmOnCheckout) {
        this.confirmOnCheckout = confirmOnCheckout;
        return this;
    }

    public MerchantDtoBuilder withCloseOnSuccess(boolean closeOnSuccess) {
        this.closeOnSuccess = closeOnSuccess;
        return this;
    }
}
