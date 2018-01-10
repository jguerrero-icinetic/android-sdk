package com.aplazame.sdk.model;

public class Article {

    /**
     The article ID.
     */
    private final String id;
    /**
     Article name.
     */
    private final String name;
    /**
     Article quantity.
     */
    private final Integer quantity;
    /**
     Article price (tax is not included).
     */
    private final Double price;
    /**
     Article url.
     */
    private final String url;
    /**
     Article image url.
     */
    private final String imageUrl;

    /*
     *  Optionals
     */

    /**
     (Optional) Article tax_rate.
     */
    private final Double taxRate;
    /**
     (Optional) The discount amount of the article.
     */
    private final Double discount;
    /**
     (Optional) The rate discount of the article.
     */
    private final Double discountRate;
    /**
     (Optional) Article description.
     */
    private final String description;

    public Article(String id, String name, Integer quantity, Double price, Double taxRate, Double discount,
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
