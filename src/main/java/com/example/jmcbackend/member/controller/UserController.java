package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserListResponse;
import com.example.jmcbackend.member.dto.UserLoginResponse;
import com.example.jmcbackend.member.service.UserService;
import com.example.jmcbackend.member.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserJoinRequest dto) {


        return ResponseEntity.ok(userService.join(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Principal principal ) {
        String userId = principal.getName();

        userService.delete(userId);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginResponse dto) {

        String token = userService.login(dto);
        return ResponseEntity.ok().body(token); // hashmap으로하세요.
    }

    @GetMapping("list")
    public ResponseEntity list() {

        List<UserListResponse> list = userService.list();

        return ResponseEntity.ok(list);
    }
}
