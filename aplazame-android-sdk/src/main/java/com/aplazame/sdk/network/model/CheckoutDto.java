package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class CheckoutDto {

    @SerializedName("toc")
    private final boolean toc;
    @SerializedName("merchant")
    private final MerchantDto merchant;
    @SerializedName("order")
    private final OrderDto order;
    @SerializedName("customer")
    private final CustomerDto customer;
    @SerializedName("billing")
    private final BillingDto billing;
    @SerializedName("shipping")
    private final ShippingDto shipping;
    @SerializedName("meta")
    private final MetaDto meta;

    public CheckoutDto(boolean toc, MerchantDto merchant, OrderDto order, CustomerDto customer,
            BillingDto billing, ShippingDto shipping, MetaDto meta) {
        this.toc = toc;
        this.merchant = merchant;
        this.order = order;
        this.customer = customer;
        this.billing = billing;
        this.shipping = shipping;
        this.meta = meta;
    }

    public boolean isToc() {
        return toc;
    }

    public MerchantDto getMerchant() {
        return merchant;
    }

    public OrderDto getOrder() {
        return order;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public BillingDto getBilling() {
        return billing;
    }

    public ShippingDto getShipping() {
        return shipping;
    }

    public MetaDto getMeta() {
        return meta;
    }
}
