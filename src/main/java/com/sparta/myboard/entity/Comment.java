package com.sparta.myboard.entity;

import com.sparta.myboard.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;  // 댓글 내용


    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 작성자

    public Comment(CommentRequestDto commentRequestDto, Post post, User user) {
        this.comment = commentRequestDto.getComment();
        this.username = user.getUsername();
        this.post = post;
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }


}

