package org.example.movie_api.comment.controller;


import lombok.AllArgsConstructor;
import org.example.movie_api.comment.service.CommentService;
import org.example.movie_api.comment.dto.CommentDto;
import org.example.movie_api.comment.entity.Comment;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    // entity로 반환하지 말라는게 어떤건지?
    // 예외처리 예상 : 작성 시 권한 확인할때, 확인 시 글 없을때, 수정 시 글 없을때, 삭제 시 없을 때


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

    // 댓글 수정
    @PostMapping("/update/{id}")
    public Comment commentUpdate(@PathVariable Long id, @RequestBody CommentDto commentDto) {

        return commentService.updateComment(id, commentDto);

    }

    // 댓글 삭제
    @DeleteMapping("/delete/{id}")
    public String commentDelete(@PathVariable Long id) {

        return commentService.deleteComment(id);

    }
}

