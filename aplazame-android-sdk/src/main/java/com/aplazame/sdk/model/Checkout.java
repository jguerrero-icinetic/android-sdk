package com.aplazame.sdk.model;

import com.aplazame.sdk.BuildConfig;

public class Checkout {
    String metaVersion = BuildConfig.VERSION_NAME;

    public boolean toc;
    public Merchant merchant;
    public Order order;
    public Customer customer;
    public Billing billing;
    public Shipping shipping;
    public Meta meta;

    public Checkout() {
        meta = new Meta();
        meta.version = metaVersion;
    }
}
