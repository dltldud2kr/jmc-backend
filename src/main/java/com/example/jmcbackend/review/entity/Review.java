package com.example.jmcbackend.review.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long storeId;
    private String userId;
    private String storeName;
    // 이미지도 넣을 것 private String review_img
    private Float reviewScore;

    @Column(length = 1000)
    private String reviewText;

    private LocalDateTime reviewCreated;


}
