package com.example.jmcbackend.dailyStore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyStoreDto {
    private String storeName;


    private Long reviewCount;
    private Float reviewAvg ;
    private Long likeCount;

}
