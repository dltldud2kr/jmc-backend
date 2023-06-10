package com.example.jmcbackend.review.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.member.repository.UserRepository;
import com.example.jmcbackend.review.dto.*;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewCreateResDto add(ReviewCreateReqDto dto , String userId, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (store.getUserId().equals(userId)){
            throw new IllegalArgumentException("User does not have permission to add a review for this store");
        }

        Review review = Review.builder()
                .user(user)
                .store(store)
                .reviewScore(dto.getReviewScore())
                .reviewText(dto.getReviewText())
                .reviewImg(dto.getReviewImg())
                .reviewCreated(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        ReviewCreateResDto reviewCreateResDto = ReviewCreateResDto.builder()
                .reviewId(review.getReviewId())
                .reviewScore(review.getReviewScore())
                .reviewImg(review.getReviewImg())
                .reviewText(review.getReviewText())
                .storeId(review.getStore().getStoreId())
                .build();


        return reviewCreateResDto;
    }


    @Override
    public ResponseEntity del(Long reviewId, String userId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if(!optionalReview.isPresent()){
            throw new IllegalStateException("게시글이 존재하지 않습니다.");
        }

        Review review = optionalReview.get();
        User user = review.getUser();

        if(user == null) {
            throw new IllegalStateException("게시글 작성자 정보가 없습니다.");
        }
        if (user.getUserId().equals(userId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalStateException("자신의 게시글이 아닙니다.");

        }

        return ResponseEntity.ok().body("삭제완료");
    }

    @Override
    public ResponseEntity update(String userId, Long reviewId, ReviewUpdateDto dto) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalStateException("Cannot find store with reviewId" + reviewId));

        User user = review.getUser();
        if(!user.getUserId().equals(userId)){
            throw new AppException(ErrorCode.UN_AUTHORIZED,"You do not have permission to modify this review.");
        }

        review.setReviewScore(dto.getReviewScore());
        review.setReviewText(dto.getReviewText());
        review.setReviewImg(dto.getReviewImg());
        review.setReviewCreated(review.getReviewCreated());
        review.setReviewUpdated(LocalDateTime.now());

        reviewRepository.save(review);

        return ResponseEntity.ok().body("수정완료");

    }

    @Override
    public Page<MyReviewListDto> myReviewList(String userId, Pageable pageable) {

        Page<Review> review = reviewRepository.findAllByUserUserId(userId, pageable);
        Page<MyReviewListDto> reviewDtoPage = review.map(MyReviewListDto::of);
        return reviewDtoPage;
    }

    @Override
    public Page<StoreReviewListDto> storeReviewList(Long storeId, Pageable pageable) {

        Page<Review> review = reviewRepository.findAllByStoreStoreId(storeId, pageable);
        Page<StoreReviewListDto> reviewDtoPage = review.map(StoreReviewListDto::of);

        return reviewDtoPage;
    }


}
