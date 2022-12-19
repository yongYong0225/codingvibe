package com.sparta.myboard.dto;

import com.sparta.myboard.entity.Post;

public class MainPostResponseDto {
    private String nickname;
    private String title;
    private String youtubeUrl;
    private Long likeCount;



    public MainPostResponseDto(Post post) {
        this.nickname = post.getNickname();
        this.youtubeUrl = post.getYoutubeUrl();
        this.title = post.getTitle();
    }


    public MainPostResponseDto(Post post, Long likeCount) {
        this.nickname = post.getNickname();
        this.title = post.getTitle();
        this.likeCount = likeCount;
    }

}






