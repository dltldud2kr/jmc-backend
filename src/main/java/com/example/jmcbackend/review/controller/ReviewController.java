package com.example.jmcbackend.review.controller;

import com.example.jmcbackend.review.dto.ReviewCreateReqDto;
import com.example.jmcbackend.review.dto.ReviewCreateResDto;
import com.example.jmcbackend.review.dto.ReviewUpdateDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @PostMapping("/storeId/{storeId}")
    public ResponseEntity<ReviewCreateResDto> writeReview(Principal principal, @RequestBody ReviewCreateReqDto dto,
                                              @PathVariable("storeId") Long storeId) {

        String userId = principal.getName();
        ReviewCreateResDto result = reviewService.add(dto, userId, storeId);

        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{reviewId}/reviewEdit")
    public ResponseEntity delReview(Principal principal, @PathVariable("reviewId") Long reviewId) {

        String userId = principal.getName();
        ResponseEntity result = reviewService.del(reviewId,userId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/{reviewId}/reviewEdit")
    public ResponseEntity reviewEdit(@PathVariable("reviewId")Long reviewId, Principal principal, @RequestBody ReviewUpdateDto dto) {
        String userId = principal.getName();

        ResponseEntity result = reviewService.update(userId,reviewId,dto);

        return ResponseEntity.ok(result);
    }



}
