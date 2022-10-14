package com.codevui.realworldapp.service;

import java.util.Map;

import com.codevui.realworldapp.exception.custom.CustomNotFoundException;
import com.codevui.realworldapp.model.article.dto.ArticleDTOCreate;
import com.codevui.realworldapp.model.article.dto.ArticleDTOFilter;
import com.codevui.realworldapp.model.article.dto.ArticleDTOResponse;

public interface ArticleService {

    public Map<String, ArticleDTOResponse> createArticles(Map<String, ArticleDTOCreate> articleDTOCreateMap);

    public Map<String, ArticleDTOResponse> getArticleBySlug(String slug) throws CustomNotFoundException;

    public Map<String, Object> getListArticle(ArticleDTOFilter articleDTOFilter);

    public Map<String, Object> getNewFeed(ArticleDTOFilter articleDTOFilter);

}
