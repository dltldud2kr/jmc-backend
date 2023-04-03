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
    private String storeUrl;
    private String storeAddress;
    private String storePhone;
    private String storeIntroduction;
    private String storeOpeningDateAndHours;
    private Long categoryId;
    private LocalDateTime StoreUpdated;
}
