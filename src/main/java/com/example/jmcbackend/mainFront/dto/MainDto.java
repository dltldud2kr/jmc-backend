package com.example.jmcbackend.mainFront.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MainDto {

    private String storeName;
    private String storeAddress;
    private Long reviewCount;
    private Float reviewAvg;
    private Long likeCount;
}
