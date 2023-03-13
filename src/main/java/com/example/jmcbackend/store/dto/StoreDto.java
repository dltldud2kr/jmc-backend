package com.example.jmcbackend.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class StoreDto {


        private Long storeId;

        private String storeName;

        private Long storeLikeCount;
        private Long storeReviewCount;

        private Long categoryId;

        private String storePhone;
        private String storeAddress;
        private String storeUrl;

        private String storeOpeningDateAndHours;    //
        private String storeInfo;

        private String userId;


}


