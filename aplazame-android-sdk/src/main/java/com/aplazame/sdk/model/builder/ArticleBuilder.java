package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Article;

public class ArticleBuilder {

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

    public ArticleBuilder() {}

    public Article create() {
        return new Article(id, name, quantity, price, taxRate, discount, discountRate, description, url, imageUrl);
    }

    public ArticleBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ArticleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ArticleBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ArticleBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public ArticleBuilder withTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public ArticleBuilder withDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public ArticleBuilder withDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public ArticleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticleBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public ArticleBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
