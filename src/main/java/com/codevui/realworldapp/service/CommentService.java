package com.codevui.realworldapp.service;

import java.util.Map;

import com.codevui.realworldapp.model.comment.dto.CommentDTOCreate;
import com.codevui.realworldapp.model.comment.dto.CommentDTOResponse;

public interface CommentService {

    Map<String, CommentDTOResponse> addComment(Map<String, CommentDTOCreate> commentDTOCreateMap);

    Map<String, CommentDTOResponse> getCommentFromArticle();

}
