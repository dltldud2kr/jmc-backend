package com.example.jmcbackend.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoreDto {


        private Long storeId;

        private String storeName;

        private Long storeLikeCount;
        private Long storeReviewCount;

        private Long categoryId;

        private String phone;
        private String address;
        private String url;
        private String userId;

        private String openTime;    //
        private String introduction;
        private Float reviewAvg;        //소수점 1자리수까지





}


