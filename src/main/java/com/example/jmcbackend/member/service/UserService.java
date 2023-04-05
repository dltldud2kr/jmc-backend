package com.example.jmcbackend.member.service;

import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserListResponse;
import com.example.jmcbackend.member.dto.UserLoginResponse;
import com.example.jmcbackend.member.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * 회원 등록
     */
    ResponseEntity join(UserJoinRequest dto);

    /**
     * 유저 로그인
     */
    String login(UserLoginResponse dto);

    /**
     * 계정 삭제
     */
    ResponseEntity delete(String userId);

    /**
     * 내 정보
     */
    UserDto myInfo(String userId);

    /**
     * 정보 수정
     */
    ResponseEntity modify(String userId, UserDto dto);

    /**
     *  유저 리스트
     */
    List<UserListResponse> list ();
}
