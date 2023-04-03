package com.example.jmcbackend.review.controller;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @PostMapping("/storeId/{storeId}")
    public ResponseEntity<Review> writeReview(Principal principal, @RequestBody ReviewDto dto,
                                              @PathVariable("storeId") Long storeId) {

        String userId = principal.getName();
        Review result = reviewService.add(dto, userId, storeId);

        return ResponseEntity.ok(result);
    }



    @GetMapping("/myReviewList")
    public ResponseEntity myReviewList(Principal principal, Pageable pageable) {
        String userId = principal.getName();

        Page<ReviewDto> reviews = reviewService.myReviewList(userId, pageable);

        return ResponseEntity.ok(reviews);
    }



}
