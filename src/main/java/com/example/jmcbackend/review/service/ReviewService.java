package com.example.jmcbackend.review.service;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    /**
     * 리뷰 작성
     */
    Review add(ReviewDto dto, String userId, Long storeId);

    /**
     * 리뷰 삭제
     */
    ResponseEntity del (Long reviewId, String userId);

    /**
     * 내 리뷰 리스트
     */
    List<Review> myReviewList(String userId);
}
