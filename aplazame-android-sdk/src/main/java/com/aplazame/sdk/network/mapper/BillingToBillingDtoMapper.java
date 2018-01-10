package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Billing;
import com.aplazame.sdk.network.model.BillingDto;
import com.aplazame.sdk.network.model.builder.BillingDtoBuilder;
import com.aplazame.sdk.network.utils.MapperUtils;

public class BillingToBillingDtoMapper implements Mapper<Billing, BillingDto> {

    @Override
    public BillingDto transformDomainToDto(Billing billing) {

        MapperUtils mapperUtils = new MapperUtils();

        BillingDto billingDto = new BillingDtoBuilder()
                .withFirstName(billing.getFirstName())
                .withLastName(billing.getLastName())
                .withPhone(billing.getAddress().getPhone())
                .withAltPhone(billing.getAddress().getAltPhone())
                .withStreet(billing.getAddress().getStreet())
                .withAddressAddition(billing.getAddress().getAddressAddition())
                .withCity(billing.getAddress().getCity())
                .withState(billing.getAddress().getState())
                .withCountry(mapperUtils.localeCountryToString(billing.getAddress().getCountry()))
                .withPostCode(billing.getAddress().getPostCode())
                .create();

        return billingDto;
    }
}
