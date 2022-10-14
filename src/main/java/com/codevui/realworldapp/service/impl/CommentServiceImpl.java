package com.codevui.realworldapp.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.codevui.realworldapp.model.comment.dto.CommentDTOCreate;
import com.codevui.realworldapp.model.comment.dto.CommentDTOResponse;
import com.codevui.realworldapp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public Map<String, CommentDTOResponse> addComment(Map<String, CommentDTOCreate> commentDTOCreateMap) {

        return null;
    }

    @Override
    public Map<String, CommentDTOResponse> getCommentFromArticle() {
        // TODO Auto-generated method stub
        return null;
    }

}
