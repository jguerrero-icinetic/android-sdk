package com.aplazame.sdk.model;

public class Checkout {

    private final Merchant merchant;
    private final Order order;
    private final Customer customer;
    private final Billing billing;
    private final Shipping shipping;

    public Checkout(Merchant merchant, Order order, Customer customer, Billing billing, Shipping shipping) {
        this.merchant = merchant;
        this.order = order;
        this.customer = customer;
        this.billing = billing;
        this.shipping = shipping;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Billing getBilling() {
        return billing;
    }

    public Shipping getShipping() {
        return shipping;
    }
}
