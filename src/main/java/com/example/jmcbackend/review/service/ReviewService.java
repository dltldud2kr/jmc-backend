package com.example.jmcbackend.review.service;

import com.example.jmcbackend.review.dto.*;
import com.example.jmcbackend.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    /**
     * 리뷰 작성
     */
    ReviewCreateResDto add(ReviewCreateReqDto dto, String userId, Long storeId);

    /**
     * 리뷰 삭제
     */
    ResponseEntity del (Long reviewId, String userId);

    /**
     * 리뷰 수정
     */
    ResponseEntity update (String userId, Long reviewId, ReviewUpdateDto reviewUpdateDto);

    /**
     * 내 리뷰 리스트
     */
    Page<MyReviewListDto> myReviewList(String userId, Pageable pageable);

    /**
     * 가게 리뷰 리스트
     */

    Page<StoreReviewListDto> storeReviewList(Long storeId, Pageable pageable);
}
