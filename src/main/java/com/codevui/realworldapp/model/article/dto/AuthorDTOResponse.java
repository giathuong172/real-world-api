package com.codevui.realworldapp.model.article.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTOResponse {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
