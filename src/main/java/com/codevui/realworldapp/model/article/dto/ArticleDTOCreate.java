package com.codevui.realworldapp.model.article.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTOCreate {
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
}
