package com.example.jmcbackend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userId;
    private String userName;
    private String password;
    private String userNickname;
    private LocalDateTime regDt;


}
