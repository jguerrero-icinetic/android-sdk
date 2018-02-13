package com.aplazame.sdk.model;

import com.aplazame.sdk.network.utils.MapperUtils;

import java.util.Locale;

public class Shipping extends Address {

    public String first_name;
    public String last_name;
    public Integer price;
    public String name;
    public Integer tax_rate;
    public Integer discount;
    public Integer discount_rate;

    public void setCountry(Locale country) {
        this.country = MapperUtils.localeCountryToString(country);
    }

    public void setPrice(Double price) {
        this.price = MapperUtils.doubleToDecimal(price);
    }

    public void setTaxRate(Double taxRate) {
        this.tax_rate = MapperUtils.doubleToDecimal(taxRate);
    }

    public void setDiscount(Double discount) {
        this.discount = MapperUtils.doubleToDecimal(discount);
    }

    public void setDiscountRate(Double discountRate) {
        this.discount_rate = MapperUtils.doubleToDecimal(discountRate);
    }
}
