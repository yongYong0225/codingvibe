package com.sparta.myboard.dto;


import com.sparta.myboard.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long postId;
    private String nickname;
    private String title;
    private String youtubeUrl;
    private String content;
    private LocalDateTime createdAt;
    private int likeCount;



    public PostResponseDto(Post entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.nickname = entity.getUser().getNickname();
        this.youtubeUrl = entity.getYoutubeUrl();
        this.content = entity.getContent();
        this.likeCount = entity.getLikeCount();
        this.createdAt = entity.getCreatedAt();
    }

    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post, List<CommentResponseDto> commentList) { // 좋아요 체크 넣으시면 됩니다:)
        this.postId = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.youtubeUrl = post.getYoutubeUrl();
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.commentList = commentList;
    }


}