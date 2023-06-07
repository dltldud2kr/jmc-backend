package com.example.jmcbackend.mainFront.dto;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MainDto {

    private Long storeId;
    private Long categoryId;
    private String contactNumber;
    private Long storeLikeCount;
    private Long storeReviewCount;
    private String thumbnailImg;
    private CityEnum regionCode;
    private String openTime;
    private String introduction;
    private String userId;
    private LocalDateTime storeCreated;
    private LocalDateTime storeUpdated;
    private String storeName;
    private String address;
    private Float reviewAvg;

}
