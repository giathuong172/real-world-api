package com.codevui.realworldapp.model.article.mapper;

import java.util.Date;

import com.codevui.realworldapp.entity.Article;
import com.codevui.realworldapp.entity.User;
import com.codevui.realworldapp.model.article.dto.ArticleDTOCreate;
import com.codevui.realworldapp.model.article.dto.ArticleDTOResponse;
import com.codevui.realworldapp.model.article.dto.AuthorDTOResponse;
import com.codevui.realworldapp.util.SlugUtil;

public class ArticleMapper {

    public static Article toArticle(ArticleDTOCreate articleDTOCreate) {
        Article article = Article.builder().slug(SlugUtil.getSlug(articleDTOCreate.getTitle()))
                .title(articleDTOCreate.getTitle())
                .description(articleDTOCreate.getDescription())
                .body(articleDTOCreate.getBody()).createdAt(new Date()).updatedAt(new Date()).build();

        article.setTagList(articleDTOCreate.getTagList());
        return article;
    }

    private static AuthorDTOResponse toAuthorDTOResponse(User author, boolean isFollowing) {
        return AuthorDTOResponse.builder().username(author.getUsername()).bio(author.getBio()).image(author.getImage())
                .following(isFollowing)
                .build();

    }

    public static ArticleDTOResponse toArticleDTOResponse(Article article, boolean favorited, int favoritesCount,
            boolean isFollowing) {

        return ArticleDTOResponse.builder().slug(article.getSlug())
                .title(article.getTitle())
                .description(article.getDescription()).body(article.getBody()).tagList(article.getTagList())
                .createdAt(article.getCreatedAt()).updatedAt(article.getUpdatedAt()).favorited(favorited)
                .favoritesCount(favoritesCount).author(toAuthorDTOResponse(article.getAuthor(), isFollowing)).build();
    }

}
