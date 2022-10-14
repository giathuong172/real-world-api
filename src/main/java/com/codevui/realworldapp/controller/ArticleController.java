package com.codevui.realworldapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codevui.realworldapp.exception.custom.CustomNotFoundException;
import com.codevui.realworldapp.model.article.dto.ArticleDTOCreate;
import com.codevui.realworldapp.model.article.dto.ArticleDTOFilter;
import com.codevui.realworldapp.model.article.dto.ArticleDTOResponse;
import com.codevui.realworldapp.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    public Map<String, ArticleDTOResponse> createArticles(
            @RequestBody Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        return articleService.createArticles(articleDTOCreateMap);
    }

    @GetMapping("/{slug}")
    public Map<String, ArticleDTOResponse> getArticleBySlug(
            @PathVariable String slug) throws CustomNotFoundException {
        return articleService.getArticleBySlug(slug);
    }

    @GetMapping()
    public Map<String, Object> getListArticle(@RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset) {
        ArticleDTOFilter articleDTOFilter = ArticleDTOFilter.builder().tag(tag).author(author).favorited(favorited)
                .limit(limit).offset(offset)
                .build();
        return articleService.getListArticle(articleDTOFilter);
    }

    @GetMapping("/feed")
    public Map<String, Object> newFeed(
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset) {
        ArticleDTOFilter articleDTOFilter = ArticleDTOFilter.builder()
                .limit(limit).offset(offset)
                .build();
        return articleService.getNewFeed(articleDTOFilter);
    }

}
