package org.example.movie_api.comment.service;


import lombok.AllArgsConstructor;
import org.example.movie_api.comment.dto.CommentDto;
import org.example.movie_api.comment.entity.Comment;
import org.example.movie_api.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

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

    // 댓글 삭제
    public String deleteComment(Long id) {

        commentRepository.deleteById(id);

        return "댓글이 삭제 되었습니다.";

    }
}
