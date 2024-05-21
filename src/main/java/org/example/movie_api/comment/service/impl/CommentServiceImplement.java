package org.example.movie_api.comment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.movie_api.comment.dto.CommentDto;
import org.example.movie_api.comment.entity.Comment;
import org.example.movie_api.comment.repository.CommentRepository;
import org.example.movie_api.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
    // orElse(null)사용 comment가 값을 포함하고있으면 값 아닐경우 null 반환.
    @Override
    public ResponseEntity<CommentDto> detailOne(Long id) {

        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment != null) {
            CommentDto detailCommentDto = new CommentDto();
            detailCommentDto.setUsername(comment.getUsername());
            detailCommentDto.setContent(comment.getContent());

            return ResponseEntity.status(HttpStatus.OK).body(detailCommentDto);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
//        Optional<Comment> optionalComment = commentRepository.findById(id);
//
//        if (optionalComment.isPresent()) {
//            Comment comment = optionalComment.get();
//            CommentDto detailCommentDto = new CommentDto();
//            detailCommentDto.setUsername(comment.getUsername());
//            detailCommentDto.setContent(comment.getContent());
//
//            return ResponseEntity.status(HttpStatus.OK).body(detailCommentDto);
//
//        } else {
//            CommentDto errorDto = new CommentDto();
//            errorDto.setUsername("댓글이 존재하지 않습니다.");
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
//        }
    }

    // 댓글 수정
    // Optionam 사용 error메시지 Dto에 할당 후 보여줌
    @Override
    public ResponseEntity<CommentDto> updateComment(Long id, CommentDto commentDto) {

        Optional<Comment> optionalComment1 = commentRepository.findById(id);

        if (optionalComment1.isPresent()) {
            Comment comment = optionalComment1.get();
            comment.setUsername(commentDto.getUsername());
            comment.setContent(commentDto.getContent());
            commentRepository.save(comment);

            CommentDto updateComentDto = new CommentDto();

            updateComentDto.setUsername(comment.getUsername());
            updateComentDto.setContent(comment.getContent());


            return ResponseEntity.status(HttpStatus.CREATED).body(updateComentDto);

        } else {
            CommentDto errorDto = new CommentDto();
            errorDto.setUsername("수정이 완료되지 않았습니다.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);

        }

    }

    @Override
    public ResponseEntity<Void> deleteComment(Long id) {

        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }
}
