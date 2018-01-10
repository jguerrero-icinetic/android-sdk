package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.ArticleDto;
import com.aplazame.sdk.network.model.OrderDto;

import java.util.List;

public class OrderDtoBuilder {

    private String id;
    private String currency;
    private Double totalAmount;
    private Double taxRate;
    private Double discount;
    private Double discountRate;
    private Double cartRate;
    private Double cartDiscountRate;
    private List<ArticleDto> articles;

    public OrderDtoBuilder() {}

    public OrderDto create() {
        return new OrderDto(id, currency, totalAmount, taxRate, discount, discountRate, cartRate, cartDiscountRate, articles);
    }

    public OrderDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public OrderDtoBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public OrderDtoBuilder withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderDtoBuilder withTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public OrderDtoBuilder withDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public OrderDtoBuilder withDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public OrderDtoBuilder withcartRate(Double cartRate) {
        this.cartRate = cartRate;
        return this;
    }

    public OrderDtoBuilder withCartDiscountRate(Double cartDiscountRate) {
        this.cartDiscountRate = cartDiscountRate;
        return this;
    }

    public OrderDtoBuilder withArticles(List<ArticleDto> articles) {
        this.articles = articles;
        return this;
    }
}
