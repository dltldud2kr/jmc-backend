package com.example.jmcbackend.review.controller;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/reviews")
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

    @DeleteMapping("/del/{reviewId}")
    public ResponseEntity<Review> delReview(Principal principal, @PathVariable("reviewId") Long reviewId) {

        String userId = principal.getName();
        reviewService.del(reviewId,userId);

        return ResponseEntity.ok(null);
    }
}
