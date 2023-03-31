package com.example.jmcbackend.review.dto;

import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDto {

    private Long reviewId;
    private Float reviewScore;
    private String reviewText;
    private String storeName;


    public static ReviewDto of(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        // Review 객체의 필드값을 ReviewDto 객체에 복사
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setReviewScore(review.getReviewScore());
        reviewDto.setReviewText(review.getReviewText());
        reviewDto.setStoreName(review.getStoreName());
        return reviewDto;
    }
}



