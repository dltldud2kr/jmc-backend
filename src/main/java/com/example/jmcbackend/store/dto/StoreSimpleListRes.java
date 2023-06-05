package com.example.jmcbackend.store.dto;

import com.example.jmcbackend.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoreSimpleListRes {
        private Long storeId; //스토어 아이디
        private String storeName; //스토어 이름
        private Long storeLikeCount; //좋아요 개수
        private Long storeReviewCount; //리뷰 개수
        private Float reviewAvg;        //리뷰 점수
        private String thumbntailImg; // or store logo
        private Long categoryId; // 음식점 분류 카테고리
        private String contactNumber; //컨택트 번호
        private String address; // 주소
        private String ownerUserId; //주인장 유저 아이디
        private String openTime; // 운영시간
        private String introduction; // 간단 가게 소개

        //작업중
        public StoreSimpleListRes fromEntity(Store store){
                return  StoreSimpleListRes.builder()
                        .storeId(store.getStoreId())
                        .storeName(store.getStoreName()).build();
        }




}


