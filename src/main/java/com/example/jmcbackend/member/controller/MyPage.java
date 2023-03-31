package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.MypageDto;
import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.service.UserService;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.service.StoreService;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import com.example.jmcbackend.storeLike.service.StoreLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPage {

    private final UserService userService;
    private final StoreService storeService;
    private final ReviewService reviewService;
    private final StoreLikeService storeLikeService;

    @GetMapping
    public ResponseEntity getMyPage(Principal principal, Pageable pageable){
        String userId = principal.getName();

        List<StoreDto> storeList = storeService.myStoreList(userId);
        Page<ReviewDto> reviewList = reviewService.myReviewList(userId,pageable);
        Page<StoreLikeDto> storeLikeList = storeLikeService.myLikeList(userId, pageable);
        UserDto userDto = userService.myInfo(userId);

        MypageDto mypageDto = new MypageDto();
        mypageDto.setStoreList(storeList);
        mypageDto.setReviewList(reviewList);
        mypageDto.setStoreLikeList(storeLikeList);
        mypageDto.setUser(userDto);


        return ResponseEntity.ok(mypageDto);
    }
}
