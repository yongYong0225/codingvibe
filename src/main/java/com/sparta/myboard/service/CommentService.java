package com.sparta.myboard.service;

import com.sparta.myboard.dto.CommentRequestDto;
import com.sparta.myboard.dto.CommentResponseDto;
import com.sparta.myboard.dto.MsgResponseDto;
import com.sparta.myboard.entity.Comment;
import com.sparta.myboard.entity.Post;
import com.sparta.myboard.entity.User;
import com.sparta.myboard.repository.CommentRepository;
import com.sparta.myboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = commentRepository.save(new Comment(commentRequestDto, post, user));

        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public MsgResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto, User user) {

        if (commentRepository.existsByIdAndUser(commentId, user)) {
            Comment commnet = commentRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
            );
            commnet.update(commentRequestDto);
            return new MsgResponseDto("댓글이 수정되었습니다.");
        } else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
    }

    // 댓글 삭제
    @Transactional
    public MsgResponseDto deleteComment(Long commentId, User user ) {
        if (commentRepository.existsByIdAndUser(commentId, user)){
            commentRepository.deleteById(commentId);
            return new MsgResponseDto("댓글이 삭제되었습니다.");
        } else {
            throw new IllegalArgumentException("댓글 삭제 실패");
        }
    }
}