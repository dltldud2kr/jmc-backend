package com.example.jmcbackend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private String userId;
    private String userName;
    private String userNickname;
    private LocalDateTime regDt;
}
