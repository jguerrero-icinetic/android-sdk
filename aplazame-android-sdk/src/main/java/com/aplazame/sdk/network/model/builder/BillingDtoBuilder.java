package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.BillingDto;

public class BillingDtoBuilder {

    private String firstName;
    private String lastName;

    // Address
    private String phone;
    private String altPhone;
    private String street;
    private String addressAddition;
    private String city;
    private String state;
    private String country;
    private String postCode;

    public BillingDtoBuilder() {}

    public BillingDto create() {
        return new BillingDto(firstName, lastName, phone, altPhone, street, addressAddition, city, state, country, postCode);
    }

    public BillingDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BillingDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BillingDtoBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public BillingDtoBuilder withAltPhone(String altPhone) {
        this.altPhone = altPhone;
        return this;
    }

    public BillingDtoBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public BillingDtoBuilder withAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
        return this;
    }

    public BillingDtoBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public BillingDtoBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public BillingDtoBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public BillingDtoBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
