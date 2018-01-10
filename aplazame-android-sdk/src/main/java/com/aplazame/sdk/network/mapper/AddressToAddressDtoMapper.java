package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Address;
import com.aplazame.sdk.network.model.AddressDto;
import com.aplazame.sdk.network.model.builder.AddressDtoBuilder;
import com.aplazame.sdk.network.utils.MapperUtils;

public class AddressToAddressDtoMapper implements Mapper<Address, AddressDto> {

    @Override
    public AddressDto transformDomainToDto(Address address) {

        MapperUtils mapperUtils = new MapperUtils();

        AddressDto addressDto = new AddressDtoBuilder()
                .withPhone(address.getPhone())
                .withAltPhone(address.getAddressAddition())
                .withStreet(address.getStreet())
                .withAddressAddition(address.getAddressAddition())
                .withCity(address.getCity())
                .withState(address.getState())
                .withCountry(mapperUtils.localeCountryToString(address.getCountry()))
                .withPostCode(address.getPostCode())
                .create();

        return addressDto;
    }
}
