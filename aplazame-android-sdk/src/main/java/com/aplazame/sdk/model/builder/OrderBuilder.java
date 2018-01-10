package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Article;
import com.aplazame.sdk.model.Order;

import java.util.Currency;
import java.util.List;

public class OrderBuilder {
    
    private String id;
    private Currency currency;
    private Double totalAmount;
    private Double taxRate;
    private Double discount;
    private Double discountRate;
    private Double cartRate;
    private Double cartDiscountRate;
    private List<Article> articles;

    public OrderBuilder() {}

    public Order create() {
        return new Order(id, currency, totalAmount, taxRate, discount, discountRate, cartRate, cartDiscountRate, articles);
    }

    public OrderBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public OrderBuilder withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderBuilder withTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public OrderBuilder withDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public OrderBuilder withDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public OrderBuilder withCartRate(Double cartRate) {
        this.cartRate = cartRate;
        return this;
    }

    public OrderBuilder withCartDiscountRate(Double cartDiscountRate) {
        this.cartDiscountRate = cartDiscountRate;
        return this;
    }

    public OrderBuilder withArticles(List<Article> articles) {
        this.articles = articles;
        return this;
    }
}
