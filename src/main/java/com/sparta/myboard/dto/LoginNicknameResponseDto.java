package com.sparta.myboard.dto;

import lombok.Getter;

@Getter
public class LoginNicknameResponseDto {

    private String nickname;


    public LoginNicknameResponseDto(String nickname) {
        this.nickname = nickname;
    }
}
