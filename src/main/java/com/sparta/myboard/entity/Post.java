package com.sparta.myboard.entity;


import com.sparta.myboard.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity // entity로 사용
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String youtubeUrl;

    @Column(nullable = false)
    private String title;

    @Column (nullable = false)
    private String content;

    @Column (nullable = false)
    private String category;

    @Column (nullable = false)
    private int likeCount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 게시글이 삭제되면 댓글도 삭제
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments = new ArrayList<>();

    public Post(PostRequestDto requestDto, User user){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
        this.category = requestDto.getCategory();
        this.youtubeUrl = requestDto.getYoutubeUrl();
    }

    public void update(PostRequestDto requestDto, User user){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
        this.category = requestDto.getCategory();
        this.youtubeUrl = requestDto.getYoutubeUrl();
    }

    public void updateLikeCount(int likeCount){

        this.likeCount += likeCount;
    }


}
