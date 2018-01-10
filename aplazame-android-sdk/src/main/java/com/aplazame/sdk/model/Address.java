package com.aplazame.sdk.model;

import java.util.Locale;

public class Address {

    /**
     Address street
     */
    private final String street;
    /**
     Address city
     */
    private final String city;
    /**
     Address state
     */
    private final String state;
    /**
     Address country code (ISO 3166-1).
     */
    private final Locale country;
    /**
     Address post code
     */
    private final String postCode;

    /*
     *  Optionals
     */

    /**
     (Optional) Address phone
     */
    private final String phone;
    /**
     (Optional) Address alternative phone.
     */
    private final String altPhone;
    /**
     (Optional) Address address addition
     */
    private final String addressAddition;

    public Address(String phone, String altPhone, String street, String addressAddition, String city,
            String state, Locale country, String postCode) {
        this.phone = phone;
        this.altPhone = altPhone;
        this.street = street;
        this.addressAddition = addressAddition;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public String getStreet() {
        return street;
    }

    public String getAddressAddition() {
        return addressAddition;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Locale getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }
}
