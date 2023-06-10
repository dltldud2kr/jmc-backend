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
public class StoreReviewListDto {

    private Long reviewId;
    private Float reviewScore;
    private String reviewText;
    private String userNickName;
    private String reviewImg;


    public static StoreReviewListDto of(Review review) {
        StoreReviewListDto storeReviewListDto = new StoreReviewListDto();
        storeReviewListDto.setReviewId(review.getReviewId());
        storeReviewListDto.setReviewScore(review.getReviewScore());
        storeReviewListDto.setReviewText(review.getReviewText());
        storeReviewListDto.setUserNickName(review.getUser().getNickname());
        storeReviewListDto.setReviewImg(review.getReviewImg());
        return storeReviewListDto;
    }
}



