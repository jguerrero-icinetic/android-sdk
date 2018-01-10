package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.AddressDto;

public class AddressDtoBuilder {

    private String phone;
    private String altPhone;
    private String street;
    private String addressAddition;
    private String city;
    private String state;
    private String country;
    private String postCode;

    public AddressDtoBuilder() {}

    public AddressDto create() {
        return new AddressDto(phone, altPhone, street, addressAddition, city, state, country, postCode);
    }

    public AddressDtoBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AddressDtoBuilder withAltPhone(String altPhone) {
        this.altPhone = altPhone;
        return this;
    }

    public AddressDtoBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressDtoBuilder withAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
        return this;
    }

    public AddressDtoBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressDtoBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressDtoBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressDtoBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
