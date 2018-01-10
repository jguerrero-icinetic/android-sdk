package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Billing;
import com.aplazame.sdk.model.Checkout;
import com.aplazame.sdk.model.Customer;
import com.aplazame.sdk.model.Merchant;
import com.aplazame.sdk.model.Order;
import com.aplazame.sdk.model.Shipping;

public class CheckoutBuilder {

    private Merchant merchant;
    private Order order;
    private Customer customer;
    private Billing billing;
    private Shipping shipping;

    public CheckoutBuilder() {}

    public Checkout create() {
        return new Checkout(merchant, order, customer, billing, shipping);
    }

    public CheckoutBuilder withMerchant(Merchant merchant) {
        this.merchant = merchant;
        return this;
    }

    public CheckoutBuilder withOrder(Order order) {
        this.order = order;
        return this;
    }

    public CheckoutBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public CheckoutBuilder withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public CheckoutBuilder withShipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }
}
