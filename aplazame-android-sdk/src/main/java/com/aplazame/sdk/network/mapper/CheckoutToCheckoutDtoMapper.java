package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.BuildConfig;
import com.aplazame.sdk.model.Checkout;
import com.aplazame.sdk.network.model.CheckoutDto;
import com.aplazame.sdk.network.model.MetaDto;
import com.aplazame.sdk.network.model.builder.CheckoutDtoBuilder;

public class CheckoutToCheckoutDtoMapper implements Mapper<Checkout, CheckoutDto> {
    @Override
    public CheckoutDto transformDomainToDto(Checkout checkout) {
        String metaVersion = BuildConfig.VERSION_NAME;

        MerchantToMerchantDtoMapper merchantToMerchantDtoMapper = new MerchantToMerchantDtoMapper();
        OrderToOrderDtoMapper orderToOrderDtoMapper = new OrderToOrderDtoMapper();
        CustomerToCustomerDtoMapper customerToCustomerDtoMapper = new CustomerToCustomerDtoMapper();
        BillingToBillingDtoMapper billingToBillingDtoMapper = new BillingToBillingDtoMapper();
        ShippingToShippingDtoMapper shippingToShippingDtoMapper = new ShippingToShippingDtoMapper();

        CheckoutDto checkoutDto = new CheckoutDtoBuilder()
                .withToc(true)
                .withMerchant(merchantToMerchantDtoMapper.transformDomainToDto(checkout.getMerchant()))
                .withOrder(orderToOrderDtoMapper.transformDomainToDto(checkout.getOrder()))
                .withCustomer(customerToCustomerDtoMapper.transformDomainToDto(checkout.getCustomer()))
                .withBilling(billingToBillingDtoMapper.transformDomainToDto(checkout.getBilling()))
                .withShipping(shippingToShippingDtoMapper.transformDomainToDto(checkout.getShipping()))
                .withMeta(new MetaDto(metaVersion))
                .create();

        return checkoutDto;
    }
}
