package com.sparta.myboard.controller;

import com.sparta.myboard.dto.CommentRequestDto;
import com.sparta.myboard.dto.CommentResponseDto;
import com.sparta.myboard.dto.MsgResponseDto;
import com.sparta.myboard.security.UserDetailsImpl;
import com.sparta.myboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/posts/{postId}")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comments")
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getUser());
    }
    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId,
                                                            @RequestBody CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }
    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public MsgResponseDto deleteComment(@PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }
}
