package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Order;
import com.aplazame.sdk.network.model.OrderDto;
import com.aplazame.sdk.network.model.builder.OrderDtoBuilder;
import com.aplazame.sdk.network.utils.MapperUtils;

public class OrderToOrderDtoMapper implements Mapper<Order, OrderDto> {

    @Override
    public OrderDto transformDomainToDto(Order order) {

        ArticlesToArticlesDtoMapper articlesToArticlesDtoMapper = new ArticlesToArticlesDtoMapper();
        MapperUtils mapperUtils = new MapperUtils();

        OrderDto orderDto = new OrderDtoBuilder()
                .withId(order.getId())
                .withCurrency(mapperUtils.currencyToString(order.getCurrency()))
                .withTotalAmount(order.getTotalAmount())
                .withTaxRate(order.getTaxRate())
                .withDiscount(order.getDiscount())
                .withDiscountRate(order.getDiscountRate())
                .withcartRate(order.getCartRate())
                .withCartDiscountRate(order.getCartDiscountRate())
                .withArticles(articlesToArticlesDtoMapper.transformDomainToDto(order.getArticles()))
                .create();

        return orderDto;
    }
}
