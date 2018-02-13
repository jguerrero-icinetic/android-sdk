package com.aplazame.sdk.model;

import com.aplazame.sdk.network.utils.MapperUtils;

public class Article {

    public String id;
    public String name;
    public Integer quantity;
    public Integer price;
    public String url;
    public String image_url;
    public Integer tax_rate;
    public Integer discount;
    public Integer discount_rate;
    public String description;

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
