package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Address;
import com.aplazame.sdk.model.Shipping;

public class ShippingBuilder {

    private String firstName;
    private String lastName;
    private Double price;
    private Double taxRate;
    private Double discount;
    private Double discounRate;
    private String name;
    private Address address;

    public ShippingBuilder() {}

    public Shipping create() {
        return new Shipping(firstName, lastName, price, taxRate, discount, discounRate, name, address);
    }

    public ShippingBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ShippingBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ShippingBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public ShippingBuilder withTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public ShippingBuilder withDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public ShippingBuilder withDiscountRate(Double discounRate) {
        this.discounRate = discounRate;
        return this;
    }

    public ShippingBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ShippingBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }
}
