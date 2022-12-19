package com.sparta.myboard.service;

import com.sparta.myboard.dto.*;
import com.sparta.myboard.entity.Comment;
import com.sparta.myboard.entity.Post;
import com.sparta.myboard.entity.PostLike;
import com.sparta.myboard.entity.User;
import com.sparta.myboard.jwt.JwtUtil;
import com.sparta.myboard.repository.PostLikeRepository;
import com.sparta.myboard.repository.PostRepository;
import com.sparta.myboard.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.RequestEntity.post;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean checkPostLike(Long postId, User user) {
        Optional<PostLike> postLike = postLikeRepository.findByPostIdAndUserId(postId, user.getId());
        return postLike.isEmpty();
    }

    // 게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        post = postRepository.save(post);
        post.setUser(user);
        return new PostResponseDto(post);
    }

    // 메인 페이지 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<MainPostResponseDto> getPosts() {
        List<MainPostResponseDto> mainPostList = new ArrayList<>();
        List<Post> postList = postRepository.findAllPostByOrderByCreatedAtDesc();
        for(Post post : postList){
            mainPostList.add(new MainPostResponseDto(post));
        }
        return mainPostList; // 최종 반환
    }


    // 상세 게시글 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id, HttpServletRequest request) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        // 새로 추가
        List<CommentResponseDto> commentList = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            commentList.add(new CommentResponseDto(comment));
        }
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null && jwtUtil.validateToken(token)) {
            //토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);
            User user = userRepository.findByLoginId(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            return new PostResponseDto(post, commentList, checkPostLike(post.getId(), user));
        }
        return new PostResponseDto(post, commentList, true);
    }

    // 게시글 수정
    @Transactional
    public MsgResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {

        if (postRepository.existsByIdAndUser(id, user)) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
            );
            post.update(requestDto, user);
            return new MsgResponseDto("게시글이 수정되었습니다.");
        } else {
            throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
        }
    }

    // 게시글 삭제
    @Transactional
    public MsgResponseDto deletePost(Long postId, User user) {
        if (postRepository.existsByIdAndUser(postId, user)){
            postRepository.deleteById(postId);
            return new MsgResponseDto("게시글이 삭제되었습니다.");
        } else {
            throw new IllegalArgumentException("게시글 삭제 실패");
        }
    }

    //카테고리별 게시글 조회
    @Transactional
    public List<MainPostResponseDto> getPostCategory(String category) {
        List<MainPostResponseDto> mainPostList = new ArrayList<>();
        List<Post> postList = postRepository.findByCategory(category);
        for(Post post: postList) {
            mainPostList.add(new MainPostResponseDto(post));
        }
        return mainPostList;
    }

}

