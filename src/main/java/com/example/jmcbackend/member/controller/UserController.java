package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserLoginRequest;
import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.member.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity join(@RequestBody UserJoinRequest dto) {

        try {
            return userServiceImpl.join(dto);
        } catch (IllegalStateException ex) {
            if(ex.getMessage().equals("exist")){
                throw new AppException(ErrorCode.USERNAME_DUPLICATED , dto.getUserId() + "는 이미 있습니다.");
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest dto) {

        String token = userServiceImpl.login(dto);
        return ResponseEntity.ok().body(token); // hashmap으로하세요.
    }
}
