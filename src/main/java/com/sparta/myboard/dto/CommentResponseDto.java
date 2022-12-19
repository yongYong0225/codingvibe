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
        this.commentId = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}

