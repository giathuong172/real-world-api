package com.codevui.realworldapp.model.comment.dto;

import java.util.Date;

import com.codevui.realworldapp.model.article.dto.AuthorDTOResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTOResponse {
    private Date createdAt;
    private Date updatedAt;
    private String body;
    private AuthorDTOResponse author;
}
