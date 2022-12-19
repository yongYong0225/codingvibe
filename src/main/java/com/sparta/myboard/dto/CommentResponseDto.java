package com.sparta.myboard.dto;

import com.sparta.myboard.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String nickname;
    private String comment;
    private LocalDateTime createdAt;

    public CommentResponseDto (Comment comment) {
        this.commentId = comment.getCommentId();
        this.nickname = comment.getUsername();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}

