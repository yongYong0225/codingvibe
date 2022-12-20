package com.sparta.myboard.service;

import com.sparta.myboard.dto.*;
import com.sparta.myboard.entity.User;
import com.sparta.myboard.entity.UserRoleEnum;
import com.sparta.myboard.jwt.JwtUtil;
import com.sparta.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void signup(SignupRequestDto signupRequestDto){
        //받아온 유저네임과 패스워드를 변수에 저장
        String loginId = signupRequestDto.getLoginId();
        String nickname = signupRequestDto.getNickname();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        //회원 중복 확인, 받아온 값이 유저레포지토리에 있는지 확인
        Optional<User> foundId = userRepository.findByLoginId(loginId);
        if (foundId.isPresent()) { //존재하는 것을 찾았다면 에러처리
            throw new IllegalArgumentException("중복된 loginId 입니다.");
        }

        Optional<User> foundNickname = userRepository.findByNickname(nickname);
        if (foundNickname.isPresent()) { //존재하는 것을 찾았다면 에러처리
            throw new IllegalArgumentException("중복된 nickname 입니다.");
        }

        //user 객체에 두 값을 저장
        User user = new User(loginId, nickname, password);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public LoginNicknameResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String loginId = loginRequestDto.getLoginId();
        String password = loginRequestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalArgumentException("등록된 ID가 없습니다.")
        );

        //비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new IllegalArgumentException("패스워드가 일치하지 않습니다");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId()));

        return new LoginNicknameResponseDto(user.getNickname());

    }

}
