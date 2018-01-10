package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDto {

    @SerializedName("id")
    private final String id;
    @SerializedName("currency")
    private final String currency;
    @SerializedName("total_amount")
    private final Double totalAmount;
    @SerializedName("tax_rate")
    private final Double taxRate;
    @SerializedName("articles")
    private final List<ArticleDto> articles;

    /*
     *   Optionals
     */

    @SerializedName("discount")
    private final Double discount;
    @SerializedName("discount_rate")
    private final Double discountRate;
    @SerializedName("cart_rate")
    private final Double cartRate;
    @SerializedName("cart_discount_rate")
    private final Double cartDiscountRate;

    public OrderDto(String id, String currency, Double totalAmount, Double taxRate, Double discount,
            Double discountRate, Double cartRate, Double cartDiscountRate, List<ArticleDto> articles) {
        this.id = id;
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.taxRate = taxRate;
        this.discount = discount;
        this.discountRate = discountRate;
        this.cartRate = cartRate;
        this.cartDiscountRate = cartDiscountRate;
        this.articles = articles;
    }

    public String getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public Double getCartRate() {
        return cartRate;
    }

    public Double getCartDiscountRate() {
        return cartDiscountRate;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }
}
