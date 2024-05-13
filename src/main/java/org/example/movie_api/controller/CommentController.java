package org.example.movie_api.controller;


import org.example.movie_api.dto.CommentDto;
import org.example.movie_api.entity.Comment;
import org.example.movie_api.service.CommentService;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.CommentToken;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping("/create")
    public Comment createComment(@RequestBody CommentDto commentDto) {

        return commentService.saveComment(commentDto);

    }

    // 댓글 확인 id로
    @GetMapping("/detail/{id}")
    public Comment commentDetail(@PathVariable Long id) {
        return commentService.detailOne(id);
    }

}
