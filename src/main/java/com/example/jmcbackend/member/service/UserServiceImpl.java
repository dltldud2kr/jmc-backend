package com.example.jmcbackend.member.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserLoginRequest;
import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.member.repository.UserRepository;
import com.example.jmcbackend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredTimeMs = 1000 * 60 * 60l;   // 1시간

    public UserDto join(UserJoinRequest dto) {

        // userName 중복 check
        userRepository.findById(dto.getUserId())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED , dto.getUserId() + "는 이미 있습니다.");
//                    throw new RuntimeException(dto.getUserId() + "는 이미 있습니다.");
                });

        // 저장
        User user = User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .userNickname(dto.getUserNickname())
                .regDt(LocalDateTime.now())
                .password(encoder.encode(dto.getPassword()))
                .build();
        userRepository.save(user);

        // 반환
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userNickname(user.getUserNickname())
                .regDt(user.getRegDt())
                .build();

        return userDto;
    }

    public String login(UserLoginRequest dto){
        // userName 없음
        User selectedUser = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->new AppException(ErrorCode.USERNAME_NOT_FOUND, dto.getUserId() + "이 없습니다."));

        // password 틀림
        if(!encoder.matches(dto.getPassword(), selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력했습니다.");
        }

        String token = JwtUtil.createJwt(selectedUser.getUserId(), secretKey, expiredTimeMs);

        // Exception 안 났을 때 토큰 발행

        return token;
    }


}
