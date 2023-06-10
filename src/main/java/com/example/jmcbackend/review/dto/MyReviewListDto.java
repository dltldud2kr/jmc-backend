package com.example.jmcbackend.review.dto;

import com.example.jmcbackend.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MyReviewListDto {

    private Long reviewId;
    private Float reviewScore;
    private String reviewText;
    private Long storeId;
    private String reviewImg;


    public static MyReviewListDto of(Review review) {
        MyReviewListDto myReviewListDto = new MyReviewListDto();
        myReviewListDto.setReviewId(review.getReviewId());
        myReviewListDto.setReviewScore(review.getReviewScore());
        myReviewListDto.setReviewText(review.getReviewText());
        myReviewListDto.setStoreId(review.getStore().getStoreId());
        myReviewListDto.setReviewImg(review.getReviewImg());
        return myReviewListDto;
    }
}



