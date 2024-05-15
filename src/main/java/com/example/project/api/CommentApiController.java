package com.example.project.api;

import com.example.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentApiController {
    @Autowired
    private CommentService commentService;
}
