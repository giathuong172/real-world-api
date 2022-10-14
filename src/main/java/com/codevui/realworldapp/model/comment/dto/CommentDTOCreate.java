package com.codevui.realworldapp.model.comment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTOCreate {
    private String body;
}
