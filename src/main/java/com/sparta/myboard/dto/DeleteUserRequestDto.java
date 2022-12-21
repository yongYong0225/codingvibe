package com.sparta.myboard.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeleteUserRequestDto {

    @NotBlank(message = "password를 입력해주세요")
    private String password;

}
