package com.example.jmcbackend.review.service;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.store.dto.StoreDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 리뷰 수정
     */
    ResponseEntity modify (String userId, Long reviewId, ReviewDto reviewDto);

    /**
     * 내 리뷰 리스트
     */
    Page<ReviewDto> myReviewList(String userId, Pageable pageable);

    /**
     * 가게 리뷰 리스트
     */

    Page<ReviewDto> storeReviewList(Long storeId, Pageable pageable);
}
