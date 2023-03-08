package com.example.jmcbackend.review.service;

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
}
