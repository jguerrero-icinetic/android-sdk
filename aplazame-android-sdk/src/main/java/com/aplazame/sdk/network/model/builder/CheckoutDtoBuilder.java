package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.BillingDto;
import com.aplazame.sdk.network.model.CheckoutDto;
import com.aplazame.sdk.network.model.CustomerDto;
import com.aplazame.sdk.network.model.MerchantDto;
import com.aplazame.sdk.network.model.MetaDto;
import com.aplazame.sdk.network.model.OrderDto;
import com.aplazame.sdk.network.model.ShippingDto;

public class CheckoutDtoBuilder {

    private boolean toc;
    private MerchantDto merchant;
    private OrderDto order;
    private CustomerDto customer;
    private BillingDto billing;
    private ShippingDto shipping;
    private MetaDto meta;

    public CheckoutDtoBuilder() {}

    public CheckoutDto create() {
        return new CheckoutDto(toc, merchant, order, customer, billing, shipping, meta);
    }

    public CheckoutDtoBuilder withToc(boolean toc) {
        this.toc = toc;
        return this;
    }

    public CheckoutDtoBuilder withMerchant(MerchantDto merchant) {
        this.merchant = merchant;
        return this;
    }

    public CheckoutDtoBuilder withOrder(OrderDto order) {
        this.order = order;
        return this;
    }

    public CheckoutDtoBuilder withCustomer(CustomerDto customer) {
        this.customer = customer;
        return this;
    }

    public CheckoutDtoBuilder withBilling(BillingDto billing) {
        this.billing = billing;
        return this;
    }

    public CheckoutDtoBuilder withShipping(ShippingDto shipping) {
        this.shipping = shipping;
        return this;
    }

    public CheckoutDtoBuilder withMeta(MetaDto meta) {
        this.meta = meta;
        return this;
    }
}
