package com.aplazame.sdk.model;

import com.aplazame.sdk.network.utils.MapperUtils;

import java.util.Locale;

public class Billing extends Address {

    public String first_name;
    public String last_name;

    public void setCountry(Locale country) {
        this.country = MapperUtils.localeCountryToString(country);
    }
}
