package com.aplazame.sdk.network.mapper;

import com.aplazame.sdk.model.Article;
import com.aplazame.sdk.network.model.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticlesToArticlesDtoMapper implements Mapper<List<Article>, List<ArticleDto>> {

    @Override
    public List<ArticleDto> transformDomainToDto(List<Article> articles) {
        List<ArticleDto> listArticlesDto = new ArrayList<>();
        ArticleToArticleDtoMapper articleToArticleDtoMapper = new ArticleToArticleDtoMapper();

        for (Article article : articles) {
            listArticlesDto.add(articleToArticleDtoMapper.transformDomainToDto(article));
        }

        return listArticlesDto;
    }
}
