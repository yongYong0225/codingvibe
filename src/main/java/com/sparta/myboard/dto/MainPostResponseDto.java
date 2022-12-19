package com.sparta.myboard.dto;

import com.sparta.myboard.entity.Post;
import lombok.Getter;


@Getter
public class MainPostResponseDto {

    private Long postId;
    private String nickname;
    private String title;
    private String youtubeUrl;
    private int likeCount;

    private boolean checkPostLike;



    public MainPostResponseDto(Post post, boolean checkPostLike) {
        this.postId = post.getId();
        this.nickname = post.getUser().getNickname();
        this.youtubeUrl = post.getYoutubeUrl();
        this.title = post.getTitle();
        this.likeCount = post.getLikeCount();
        this.checkPostLike = checkPostLike;
    }


}






