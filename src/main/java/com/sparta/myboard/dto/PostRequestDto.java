package com.sparta.myboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String youtubeUrl;
    private String content;
    private String category;

}
