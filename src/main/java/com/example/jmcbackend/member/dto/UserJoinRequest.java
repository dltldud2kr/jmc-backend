package com.example.jmcbackend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userId;
    private String userName;
    private String password;
    private String userNickname;
    private LocalDateTime regDt;


}
