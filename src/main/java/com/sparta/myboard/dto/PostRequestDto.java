package com.sparta.myboard.dto;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostRequestDto {

    private String title;
    @Pattern(regexp = "(^.*(?:(?:youtu\\.be\\/|v\\/|vi\\/|u\\/\\w\\/|embed\\/)|(?:(?:watch)?\\?v(?:i)?=|\\&v(?:i)?=))([^#\\&\\?]*).*)", message = "YoutubeUrl을 확인해주세요")
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
