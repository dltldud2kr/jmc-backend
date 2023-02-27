package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserLoginRequest;
import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.member.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody UserJoinRequest dto) {
        UserDto result = userServiceImpl.join(dto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto) {
        String token = userServiceImpl.login(dto);
        return ResponseEntity.ok().body(token);
    }


}
