package com.sparta.myboard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequestDto {
    @NotBlank(message = "id를 입력해주세요")
    private String loginId;
    @NotBlank(message = "password를 입력해주세요")
    private String password;
}
