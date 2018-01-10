package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.ArticleDto;

public class ArticleDtoBuilder {

    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private Double taxRate;
    private Double discount;
    private Double discountRate;
    private String description;
    private String url;
    private String imageUrl;

    public ArticleDtoBuilder() {}

    public ArticleDto create() {
        return new ArticleDto(id, name, quantity, price, taxRate, discount, discountRate, description, url, imageUrl);
    }

    public ArticleDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ArticleDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ArticleDtoBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ArticleDtoBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public ArticleDtoBuilder withTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public ArticleDtoBuilder withDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public ArticleDtoBuilder withDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public ArticleDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticleDtoBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public ArticleDtoBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
