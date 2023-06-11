package com.example.jmcbackend.store.dto;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoreSimpleListRes {
        private Long storeId; //스토어 아이디
        private String storeName; //스토어 이름
        private int storeLikeCount; //좋아요 개수
        private int storeReviewCount; //리뷰 개수
        private Float reviewAvg;        //리뷰 점수
        private String thumbnailImg; // or store logo
        private Long categoryId; // 음식점 분류 카테고리
        private String contactNumber; //컨택트 번호
        private String address; // 주소
        private CityEnum regionCode;    //지역
        private String ownerUserId; //주인장 유저 아이디
        private String openTime; // 운영시간
        private LocalDateTime storeCreated;
        private LocalDateTime storeUpdated;
        private String introduction; // 간단 가게 소개

        //작업중
        public StoreSimpleListRes fromEntity(Store store,long reviewCount,Float reviewAvg,long likeCount){
                return  StoreSimpleListRes.builder()
                        .storeId(store.getStoreId())
                        .storeName(store.getStoreName())
                        .storeReviewCount((int) reviewCount)
                        .reviewAvg(reviewAvg)
                        .storeLikeCount((int) likeCount)
                        .thumbnailImg(store.getThumbnailImg())
                        .categoryId(store.getCategoryId())
                        .contactNumber(store.getPhone())
                        .address(store.getAddress())
                        .regionCode(store.getRegionCode())
                        .ownerUserId(store.getUserId())
                        .openTime(store.getOpenTime())
                        .storeCreated(store.getStoreCreated())
                        .storeUpdated(store.getStoreUpdated())
                        .introduction(store.getIntroduction())
                        .build();
        }




}


