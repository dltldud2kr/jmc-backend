package com.example.jmcbackend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreEditDto {

    private String storeName;
    private String url;
    private String address;
    private String phone;
    private String introduction;
    private String openTime;
    private Long categoryId;
    private LocalDateTime StoreUpdated;
}
