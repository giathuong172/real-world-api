package com.codevui.realworldapp.model.article.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTOResponse {
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private Date createdAt;
    private Date updatedAt;
    private boolean favorited;
    private int favoritesCount;
    private AuthorDTOResponse author;
}
