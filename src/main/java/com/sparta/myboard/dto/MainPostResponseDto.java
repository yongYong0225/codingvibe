package com.sparta.myboard.dto;

import com.sparta.myboard.entity.Post;
import lombok.Getter;


@Getter
public class MainPostResponseDto {
    private String nickname;
    private String title;
    private String youtubeUrl;
    private int likeCount;



    public MainPostResponseDto(Post post) {
        this.nickname = post.getUser().getNickname();
        this.youtubeUrl = post.getYoutubeUrl();
        this.title = post.getTitle();
    }


    public MainPostResponseDto(Post post, int likeCount) {
        this.nickname = post.getUser().getNickname();
        this.youtubeUrl = post.getYoutubeUrl();
        this.title = post.getTitle();
        this.likeCount = likeCount;
    }

}






