package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Merchant;
import com.aplazame.sdk.network.model.MerchantDto;
import com.aplazame.sdk.network.model.builder.MerchantDtoBuilder;

public class MerchantToMerchantDtoMapper implements Mapper<Merchant, MerchantDto> {

    @Override
    public MerchantDto transformDomainToDto(Merchant merchantConfig) {

        MerchantDto merchantConfigDto = new MerchantDtoBuilder()
                .withTimeoutCheckout(merchantConfig.getTimeoutCheckout())
                .withTimeoutExtra(merchantConfig.getTimeoutExtra())
                .withConfirmOnCheckout(merchantConfig.isConfirmOnCheckout())
                .withCloseOnSuccess(merchantConfig.isCloseOnSuccess())
                .create();

        return merchantConfigDto;
    }
}
