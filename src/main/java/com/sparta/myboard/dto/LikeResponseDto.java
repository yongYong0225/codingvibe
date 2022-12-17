package com.sparta.myboard.dto;

import lombok.Getter;

@Getter
public class LikeResponseDto {
    private String msg;
    private Long likeCount;

    public LikeResponseDto(String msg, Long likeCount) {
        this.msg = msg;
        this.likeCount = likeCount;
    }
}
