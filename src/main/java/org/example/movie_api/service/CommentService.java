package org.example.movie_api.service;


import jakarta.persistence.Id;
import org.example.movie_api.dto.CommentDto;
import org.example.movie_api.entity.Comment;
import org.example.movie_api.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 댓글 작성
    public Comment saveComment(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setUsername(commentDto.getUsername());
        comment.setContent(commentDto.getContent());

        return commentRepository.save(comment);

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
}
