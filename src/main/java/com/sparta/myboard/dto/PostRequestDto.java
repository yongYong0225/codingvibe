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


    public void embedUrl(String youtubeUrl){

        String upload = youtubeUrl.replace("watch?v=","embed/");

        //&(플레이 리스트) 포함되 있으면 그 뒤를 다 짜른다
        if(upload.contains("&")){
            int idx = upload.indexOf("&");
            upload = upload.substring(0,idx);

        }
        this.youtubeUrl = upload;
    }

}
