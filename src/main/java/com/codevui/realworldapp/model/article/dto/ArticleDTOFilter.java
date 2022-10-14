package com.codevui.realworldapp.model.article.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTOFilter {
    private String tag;
    private String author;
    private String favorited;
    private int limit;
    private int offset;
}
