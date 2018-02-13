package com.aplazame.sdk.model;

import com.aplazame.sdk.network.utils.MapperUtils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Order {

    public String id;
    public String currency;
    public Integer total_amount;
    public Integer tax_rate;
    public List<Article> articles;
    public Integer discount;
    public Integer discount_rate;
    public Integer cart_rate;
    public Integer cart_discount_rate;

    public void setCurrency(Currency currency) {
        this.currency = MapperUtils.currencyToString(currency);
    }

    public void setTotalAmount(Double totalAmount) {
        this.total_amount = MapperUtils.doubleToDecimal(totalAmount);
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

    public void setCartRate(Double cartRate) {
        this.cart_rate = MapperUtils.doubleToDecimal(cartRate);
    }

    public void setCartDiscountRate(Double cartDiscountRate) {
        this.cart_discount_rate = MapperUtils.doubleToDecimal(cartDiscountRate);
    }
}
