package org.example.movie_api.comment.service;


import org.example.movie_api.comment.dto.CommentDto;
import org.example.movie_api.comment.entity.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<CommentDto> saveComment(CommentDto commentDto);
    // 댓글 작성

    Comment detailOne(Long id);
    // 댓글 한개 조회

    Comment updateComment(Long id, CommentDto commentDto);
    // 댓글 수정

    String deleteComment(Long id);
    // 댓글 삭제

}
