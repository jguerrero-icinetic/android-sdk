package com.aplazame.sdk.model;

import com.aplazame.sdk.network.utils.MapperUtils;

import java.util.Locale;

public class Address {

    public String street;
    public String city;
    public String state;
    public String country;
    public String postcode;
    public String phone;
    public String alt_phone;
    public String address_addition;

    public void setCountry(Locale country) {
        this.country = MapperUtils.localeCountryToString(country);
    }
}
