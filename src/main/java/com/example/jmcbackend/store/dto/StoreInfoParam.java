package com.example.jmcbackend.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class StoreInfoParam {

    private Long storeId;

    private String storeName;

    private Long storeLikeCount;
    private Long storeReviewCount;

    private Long categoryId;

    private String storePhone;
    private String storeAddress;
    private String storeUrl;

    private String storeOpeningDateAndHours;    //
    private String storeIntroduction;

    private String userId;

}
