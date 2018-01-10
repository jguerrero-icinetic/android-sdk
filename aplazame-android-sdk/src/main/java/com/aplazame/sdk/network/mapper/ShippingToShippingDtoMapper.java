package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Shipping;
import com.aplazame.sdk.network.model.ShippingDto;
import com.aplazame.sdk.network.model.builder.ShippingDtoBuilder;
import com.aplazame.sdk.network.utils.MapperUtils;

public class ShippingToShippingDtoMapper implements Mapper<Shipping, ShippingDto> {

    @Override
    public ShippingDto transformDomainToDto(Shipping shipping) {

        MapperUtils mapperUtils = new MapperUtils();

        ShippingDto shippingDto = new ShippingDtoBuilder()
                .withFirstName(shipping.getFirstName())
                .withLastName(shipping.getLastName())
                .withPhone(shipping.getAddress().getPhone())
                .withAltPhone(shipping.getAddress().getAltPhone())
                .withStreet(shipping.getAddress().getStreet())
                .withAddressAddition(shipping.getAddress().getAddressAddition())
                .withCity(shipping.getAddress().getCity())
                .withState(shipping.getAddress().getState())
                .withCountry(mapperUtils.localeCountryToString(shipping.getAddress().getCountry()))
                .withPostCode(shipping.getAddress().getPostCode())
                .withPrice(shipping.getPrice())
                .withTaxRate(shipping.getTaxRate())
                .withDiscount(shipping.getDiscount())
                .withDiscountRate(shipping.getDiscounRate())
                .withName(shipping.getName())
                .create();

        return shippingDto;
    }
}
