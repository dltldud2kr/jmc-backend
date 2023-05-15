package com.example.jmcbackend.member.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserListResponse;
import com.example.jmcbackend.member.dto.UserLoginResponse;
import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.member.repository.UserRepository;
import com.example.jmcbackend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredTimeMs = 1000 * 60 * 60l;   // 1시간

    public ResponseEntity join(UserJoinRequest dto) {

        // userName 중복 check
        userRepository.findById(dto.getUserId())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED , dto.getUserId() + "is already there.");
                });

            // 저장
            User user = User.builder()
                    .userId(dto.getUserId())
                    .userName(dto.getUserName())
                    .nickname(dto.getNickname())
                    .regDt(LocalDateTime.now())
                    .password(encoder.encode(dto.getPassword()))
                    .build();
            userRepository.save(user);
            return ResponseEntity.ok().body(null);

    }

    public String login(UserLoginResponse dto){
        // userName 없음
        User selectedUser = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->new AppException(ErrorCode.USERNAME_NOT_FOUND, dto.getUserId() + "not exist."));

        // password 틀림
        if(!encoder.matches(dto.getPassword(), selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "wrong password");
        }

        String token = JwtUtil.createJwt(selectedUser.getUserId(), secretKey, expiredTimeMs);

        // Exception 안 났을 때 토큰 발행

        return token;
    }

    @Override
    public ResponseEntity delete(String userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            userRepository.delete(user.get());
            return ResponseEntity.ok("delete success");
        } else {
            throw new AppException(ErrorCode.USERNAME_NOT_FOUND," user not found.");
        }
    }

    @Override
    public UserDto myInfo(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Cannot find user with userId " + userId));

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setNickname(user.getNickname());
        userDto.setRegDt(user.getRegDt());

        return userDto;
    }

    @Override
    public ResponseEntity modify(String userId, UserDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Cannot find user with userId " + userId));

        if (!user.getUserId().equals(userId)) {
            throw new AppException(ErrorCode.UN_AUTHORIZED, "You do not have permission to modify this store.");
        }

        user.setUserName(dto.getUserName());
        user.setNickname(dto.getNickname());
        user.setPassword(encoder.encode(dto.getPassword()));
//        user.setUdtDt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok().body("수정완료");
    }

    @Override
    public List<UserListResponse> list() {

        List<User> list = userRepository.findAll();

        List<UserListResponse> userListResponses = new ArrayList<>();
        for (User x : list)
        userListResponses.add(UserListResponse.builder()
                .userId(x.getUserId())
                        .regDt(x.getRegDt())
                        .nickname(x.getNickname())
                        .userName(x.getUserName())
                .build()
        );
        return userListResponses;
    }



}
