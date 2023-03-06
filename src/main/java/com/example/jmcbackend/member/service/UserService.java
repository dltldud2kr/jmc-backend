package com.example.jmcbackend.member.service;

import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 회원 등록
     */
    ResponseEntity join(UserJoinRequest dto);

    /**
     * 유저 로그인
     */
    String login(UserLoginRequest dto);

}
