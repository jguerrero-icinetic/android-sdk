package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Address;

import java.util.Locale;

public class AddressBuilder {
    
    private String phone;
    private String altPhone;
    private String street;
    private String addressAddition;
    private String city;
    private String state;
    private Locale country;
    private String postCode;
    
    public AddressBuilder() {}
    
    public Address create() {
        return new Address(phone, altPhone, street, addressAddition, city, state, country, postCode);
    }

    public AddressBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AddressBuilder withAltPhone(String altPhone) {
        this.altPhone = altPhone;
        return this;
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder withCountry(Locale country) {
        this.country = country;
        return this;
    }

    public AddressBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
