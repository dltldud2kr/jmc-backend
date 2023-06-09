package com.example.jmcbackend.store.dto;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoreInfoParam {

    private Long storeId;

    private String storeName;

    private Long storeLikeCount;
    private Long storeReviewCount;

    private Long categoryId;
    private CityEnum regionCode;

    private String phone;
    private String address;
    private String url;
    private String thumbnailImg;

    private String openTime;    //
    private String introduction;

    private String userId;

}
