package com.codevui.realworldapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevui.realworldapp.model.comment.dto.CommentDTOCreate;
import com.codevui.realworldapp.model.comment.dto.CommentDTOResponse;
import com.codevui.realworldapp.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles/{slug}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public Map<String, CommentDTOResponse> addComment(@RequestBody Map<String, CommentDTOCreate> commentDTOCreateMap) {
        return commentService.addComment(commentDTOCreateMap);
    }

    @GetMapping()
    public Map<String, CommentDTOResponse> getCommentFromArticle() {
        return commentService.getCommentFromArticle();
    }

}
