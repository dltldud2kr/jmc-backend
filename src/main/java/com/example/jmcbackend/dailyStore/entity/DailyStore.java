package com.example.jmcbackend.dailyStore.entity;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DailyStore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId; //스토어 아이디
    private String storeName; //스토어 이름
    private Long storeLikeCount; //좋아요 개수
    private Long storeReviewCount; //리뷰 개수
    private Float reviewAvg;        //리뷰 점수
    private String thumbnailImg; // or store logo
    private Long categoryId; // 음식점 분류 카테고리
    private String address; // 주소
    private CityEnum regionCode;    //지역
    private String userId; //주인장 유저 아이디
    private String openTime; // 운영시간
    private LocalDateTime storeCreated;
    private LocalDateTime storeUpdated;
    private String introduction; // 간단 가게 소개


}
