package com.aplazame.sdk.model;

import java.util.Currency;
import java.util.List;

public class Order {

    /**
     Your order ID.
     */
    private final String id;
    /**
     Locale for currency code of the order (ISO-4217).
     */
    private final Currency currency;
    /**
     Order total amount.
     */
    private final Double totalAmount;
    /**
     Article tax rate.
     */
    private final Double taxRate;

    /**
     Articles in cart.
     */
    private final List<Article> articles;

    /*
     *  Optionals
     */

    /**
     (Optional) The discount amount of the order.
     */
    private final Double discount;
    /**
     (Optional) The rate discount of the order.
     */
    private final Double discountRate;
    /**
     (Optional) The cart discount of the order.
     */
    private final Double cartRate;
    /**
     (Optional) The cart discount rate of the order.
     */
    private final Double cartDiscountRate;

    public Order(String id, Currency currency, Double totalAmount, Double taxRate, Double discount,
            Double discountRate, Double cartRate, Double cartDiscountRate, List<Article> articles) {
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

    public Currency getCurrency() {
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

    public List<Article> getArticles() {
        return articles;
    }
}
