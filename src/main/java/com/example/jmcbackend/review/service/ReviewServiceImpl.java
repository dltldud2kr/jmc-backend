package com.example.jmcbackend.review.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review add(ReviewDto dto , String userId, Long storeId) {
        String storeName = storeRepository.findById(storeId).get().getStoreName();


        Review review = Review.builder()
                .userId(userId)
                .storeId(storeId)
                .reviewScore(dto.getReviewScore())
                .storeName(storeName)
                .reviewText(dto.getReviewText())
                .reviewCreated(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        return review;
    }


    @Override
    public ResponseEntity del(Long reviewId, String userId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(!optionalReview.isPresent()){
            throw new IllegalStateException("게시글이 존재하지 않습니다.");
        }

        if(optionalReview.get().getUserId().equals(userId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalStateException("자신의 게시글이 아닙니다.");
        }

        return ResponseEntity.ok().body(null);
    }

    @Override
    public void modify(String userId, Long reviewId, ReviewDto dto) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("Cannot find store with reviewId" + reviewId));

        if(!review.getUserId().equals(userId)){
            throw new AppException(ErrorCode.UN_AUTHORIZED,"You do not have permission to modify this review.");
        }

        review.setReviewScore(dto.getReviewScore());
        review.setReviewText(dto.getReviewText());
        review.setReviewUpdated(LocalDateTime.now());

        reviewRepository.save(review);

    }

    @Override
    public Page<ReviewDto> myReviewList(String userId, Pageable pageable) {

        Page<Review> review = reviewRepository.findAllByUserId(userId, pageable);
        Page<ReviewDto> reviewDtoPage = review.map(ReviewDto::of);
        return reviewDtoPage;
    }

    @Override
    public Page<ReviewDto> storeReviewList(Long storeId, Pageable pageable) {

        Page<Review> review = reviewRepository.findAllByStoreId(storeId, pageable);
        Page<ReviewDto> storeReviewList = review.map(ReviewDto::of);

        return storeReviewList;
    }


}
