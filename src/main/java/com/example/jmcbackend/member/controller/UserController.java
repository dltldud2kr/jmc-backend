package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserLoginRequest;
import com.example.jmcbackend.member.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserJoinRequest dto) {
        return userServiceImpl.join(dto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest dto) {

        String token = userServiceImpl.login(dto);
        return ResponseEntity.ok().body(token); // hashmap으로하세요.
    }
}
