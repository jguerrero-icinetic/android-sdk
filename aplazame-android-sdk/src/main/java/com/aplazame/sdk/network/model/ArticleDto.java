package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class ArticleDto {

    @SerializedName("id")
    private final String id;
    @SerializedName("name")
    private final String name;
    @SerializedName("quantity")
    private final Integer quantity;
    @SerializedName("price")
    private final Double price;
    @SerializedName("url")
    private final String url;
    @SerializedName("image_url")
    private final String imageUrl;

    /*
     *  Optionals
     */

    @SerializedName("tax_rate")
    private final Double taxRate;
    @SerializedName("discount")
    private final Double discount;
    @SerializedName("discount_rate")
    private final Double discountRate;
    @SerializedName("description")
    private final String description;

    public ArticleDto(String id, String name, Integer quantity, Double price, Double taxRate, Double discount,
            Double discountRate, String description, String url, String imageUrl) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.taxRate = taxRate;
        this.discount = discount;
        this.discountRate = discountRate;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
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

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
