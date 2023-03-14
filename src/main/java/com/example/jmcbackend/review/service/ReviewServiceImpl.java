package com.example.jmcbackend.review.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review add(ReviewDto dto , String userId, Long storeId) {

        String storeName =  storeRepository.findById(storeId).get().getStoreName();

        Review review = Review.builder()
                .userId(userId)
                .storeId(storeId)
                .reviewScore(dto.getReviewScore())
                .reviewText(dto.getReviewText())
                .reviewCreated(LocalDateTime.now())
                .storeName(storeName)
                .build();

        reviewRepository.save(review);

        return review;
    }

    /**
     * 자신의 게시글에만 x(삭제) 표시가 뜨게끔 하려면 어떻게 해야할까?
     */
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
    public List<Review> myReviewList(String userId) {

        return reviewRepository.findAllByUserId(userId);
    }


}
