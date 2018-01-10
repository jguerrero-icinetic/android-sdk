package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.ShippingDto;

public class ShippingDtoBuilder {

    private String firstName;
    private String lastName;
    private Double price;
    private Double taxRate;
    private Double discount;
    private Double discounRate;
    private String name;

    // Address
    private String phone;
    private String altPhone;
    private String street;
    private String addressAddition;
    private String city;
    private String state;
    private String country;
    private String postCode;

    public ShippingDtoBuilder() {}

    public ShippingDto create() {
        return new ShippingDto(firstName, lastName, phone, altPhone, street, addressAddition, city, state, country,
            postCode, price, taxRate, discount, discounRate, name);
    }

    public ShippingDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ShippingDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ShippingDtoBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public ShippingDtoBuilder withTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public ShippingDtoBuilder withDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public ShippingDtoBuilder withDiscountRate(Double discounRate) {
        this.discounRate = discounRate;
        return this;
    }

    public ShippingDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ShippingDtoBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ShippingDtoBuilder withAltPhone(String altPhone) {
        this.altPhone = altPhone;
        return this;
    }

    public ShippingDtoBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public ShippingDtoBuilder withAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
        return this;
    }

    public ShippingDtoBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public ShippingDtoBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public ShippingDtoBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public ShippingDtoBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
