package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.MypageDto;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.storeLike.dto.StoreLikesDto;
import com.example.jmcbackend.storeLike.service.StoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final ReviewService reviewService;
    private final StoreLikesService storeLikesService;

    @GetMapping
    public ResponseEntity getMyPage(Principal principal, Pageable pageable){
        String userId = principal.getName();

        Page<ReviewDto> reviewList = reviewService.myReviewList(userId,pageable);   // 내 리뷰 작성 리스트
        Page<StoreLikesDto> storeLikeList = storeLikesService.myLikeList(userId, pageable); // 내 좋아요 가게 리스트

        MypageDto mypageDto = new MypageDto();

        mypageDto.setReviewList(reviewList);
        mypageDto.setStoreLikeList(storeLikeList);


        return ResponseEntity.ok(mypageDto);
    }



}
