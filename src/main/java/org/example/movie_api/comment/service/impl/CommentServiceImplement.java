package org.example.movie_api.comment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.movie_api.comment.dto.CommentDto;
import org.example.movie_api.comment.entity.Comment;
import org.example.movie_api.comment.repository.CommentRepository;
import org.example.movie_api.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService {

    private final CommentRepository commentRepository;

    // 댓글 작성
    @Override
    public ResponseEntity<CommentDto> saveComment(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setUsername(commentDto.getUsername());
        comment.setContent(commentDto.getContent());

        Comment savedComment = commentRepository.save(comment);

        CommentDto savedCommentDto = new CommentDto();
        savedCommentDto.setUsername(savedComment.getUsername());
        savedCommentDto.setContent(savedComment.getContent());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommentDto);

    }

    // 댓글 한개 조회
    public Comment detailOne(Long id) {

        return commentRepository.findById(id).orElse(null);

    }

    // 댓글 수정
    public Comment updateComment(Long id, CommentDto commentDto) {

        Comment comment = detailOne(id);
        comment.setUsername(commentDto.getUsername());
        comment.setContent(commentDto.getContent());

        return commentRepository.save(comment);

    }

    @Override
    public String deleteComment(Long id) {

        return "삭제가 완료 되었습니다.";

    }
}
