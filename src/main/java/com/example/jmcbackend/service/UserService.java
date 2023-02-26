package com.example.jmcbackend.service;

import com.example.jmcbackend.domain.User;
import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.repository.UserRepository;
import com.example.jmcbackend.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredTimeMs = 1000 * 60 * 60l;   // 1시간

//    public String join(String userName, String password) {
//
//        // userName 중복 check
//        userRepository.findByUserName(userName)
//                .ifPresent(user -> {
//                    throw new AppException(ErrorCode.USERNAME_DUPLICATED , userName + "는 이미 있습니다.");
////                    throw new RuntimeException(userName + "는 이미 있습니다.");
//                });
//
//        // 저장
//        User user = User.builder()
//                .userName(userName)
//                .password(encoder.encode(password))
//                .build();
//        userRepository.save(user);
//
//        return "SUCCESS";
//    }

//    public String login(String userName, String password){
//        // userName 없음
//        User selectedUser = userRepository.findByUserName(userName)
//                .orElseThrow(() ->new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));
//
//        // password 틀림
//        if(!encoder.matches(password, selectedUser.getPassword())) {
//            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력했습니다.");
//        }
//
//        String token = JwtTokenUtil.createToken(selectedUser.getUserName(), secretKey, expiredTimeMs);
//
//        // Exception 안 났을 때 토큰 발행
//
//        return token;
//    }

    public String login(String userName, String password){
        // 인증 과정 생략

        return JwtTokenUtil.createToken(userName, secretKey, expiredTimeMs);

    }
}
