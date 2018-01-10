package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Article;
import com.aplazame.sdk.network.model.ArticleDto;
import com.aplazame.sdk.network.model.builder.ArticleDtoBuilder;

public class ArticleToArticleDtoMapper implements Mapper<Article, ArticleDto> {

    @Override
    public ArticleDto transformDomainToDto(Article article) {

        ArticleDto articleDto = new ArticleDtoBuilder()
                .withId(article.getId())
                .withName(article.getName())
                .withQuantity(article.getQuantity())
                .withPrice(article.getPrice())
                .withTaxRate(article.getTaxRate())
                .withDiscount(article.getDiscount())
                .withDiscountRate(article.getDiscountRate())
                .withDescription(article.getDescription())
                .withUrl(article.getUrl())
                .withImageUrl(article.getImageUrl())
                .create();

        return articleDto;
    }
}
