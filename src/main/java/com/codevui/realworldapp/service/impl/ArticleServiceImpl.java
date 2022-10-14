package com.codevui.realworldapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codevui.realworldapp.entity.Article;
import com.codevui.realworldapp.entity.User;
import com.codevui.realworldapp.exception.custom.CustomNotFoundException;
import com.codevui.realworldapp.model.CustomError;
import com.codevui.realworldapp.model.article.dto.ArticleDTOCreate;
import com.codevui.realworldapp.model.article.dto.ArticleDTOFilter;
import com.codevui.realworldapp.model.article.dto.ArticleDTOResponse;
import com.codevui.realworldapp.model.article.mapper.ArticleMapper;
import com.codevui.realworldapp.repository.ArticleRepository;
import com.codevui.realworldapp.repository.criteria.ArticleCriteria;
import com.codevui.realworldapp.service.ArticleService;
import com.codevui.realworldapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleCriteria articleCriteria;
    private final UserService userService;

    @Override
    public Map<String, ArticleDTOResponse> createArticles(Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        ArticleDTOCreate articleDTOCreate = articleDTOCreateMap.get("article");

        Article article = ArticleMapper.toArticle(articleDTOCreate);
        User userLoggedIn = userService.getUserLoggedIn();

        article.setAuthor(userLoggedIn);
        article = articleRepository.save(article);

        User author = article.getAuthor();
        Set<User> followers = author.getFollowers();
        boolean isFollowing = false;
        for (User U : followers) {
            if (U.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        Map<String, ArticleDTOResponse> wrapper = new HashMap<>();
        ArticleDTOResponse articleDTOResponse = ArticleMapper.toArticleDTOResponse(article, false, 0, isFollowing);
        wrapper.put("article", articleDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, ArticleDTOResponse> getArticleBySlug(String slug) throws CustomNotFoundException {
        Optional<Article> articleOptional = articleRepository.findBySlug(slug);
        if (articleOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("Article not found").build());
        }
        Article article = articleOptional.get();
        User userLoggedIn = userService.getUserLoggedIn();
        User author = article.getAuthor();
        Set<User> followers = author.getFollowers();
        boolean isFollowing = false;
        for (User U : followers) {
            if (U.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        Map<String, ArticleDTOResponse> wrapper = new HashMap<>();
        ArticleDTOResponse articleDTOResponse = ArticleMapper.toArticleDTOResponse(article, false, 0, isFollowing);
        wrapper.put("article", articleDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, Object> getListArticle(ArticleDTOFilter articleDTOFilter) {
        List<ArticleDTOResponse> articleDTOResponses = new ArrayList<>();

        Map<String, Object> articleMap = articleCriteria.findAll(articleDTOFilter);
        List<Article> articles = (List<Article>) articleMap.get("listArticle");
        for (Article article : articles) {
            articleDTOResponses.add(ArticleMapper.toArticleDTOResponse(article, false, 0, true));
        }
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("article", articleDTOResponses);
        wrapper.put("articlesCount", (long) articleMap.get("articlesCount"));
        return wrapper;
    }

    @Override
    public Map<String, Object> getNewFeed(ArticleDTOFilter articleDTOFilter) {
        User currentUser = userService.getUserLoggedIn();
        Set<User> followings = currentUser.getFollowings();
        List<String> listUsername = followings.stream().map(u -> u.getUsername()).collect(Collectors.toList());

        Map<String, Object> articleMap = articleCriteria.getNewFeed(listUsername, articleDTOFilter);
        List<ArticleDTOResponse> articleDTOResponses = new ArrayList<>();
        List<Article> articles = (List<Article>) articleMap.get("listArticle");
        for (Article article : articles) {
            articleDTOResponses.add(ArticleMapper.toArticleDTOResponse(article, false, 0, true));
        }

        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("article", articleDTOResponses);
        wrapper.put("articlesCount", (long) articleMap.get("articlesCount"));
        return wrapper;
    }

}
