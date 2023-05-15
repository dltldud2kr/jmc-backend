package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.MypageDto;
import com.example.jmcbackend.member.dto.StoreEditDto;
import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.service.UserService;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.service.StoreService;
import com.example.jmcbackend.storeLike.dto.StoreLikesDto;
import com.example.jmcbackend.storeLike.service.StoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;
    private final StoreService storeService;
    private final ReviewService reviewService;
    private final StoreLikesService storeLikesService;

    @GetMapping
    public ResponseEntity getMyPage(Principal principal, Pageable pageable){
        String userId = principal.getName();

//        List<StoreDto> storeList = storeService.myStoreList(userId);      // 내가 등록한 가게 리스트
        Page<ReviewDto> reviewList = reviewService.myReviewList(userId,pageable);   // 내 리뷰 작성 리스트
        Page<StoreLikesDto> storeLikeList = storeLikesService.myLikeList(userId, pageable); // 내 좋아요 가게 리스트
        UserDto userDto = userService.myInfo(userId);       // 나의 정보

        MypageDto mypageDto = new MypageDto();
//        mypageDto.setStoreList(storeList);
        mypageDto.setReviewList(reviewList);
        mypageDto.setStoreLikeList(storeLikeList);
//        mypageDto.setUser(userDto); //자기 정보는 다른 페이지 드가야 뜨게해야할듯함. 닉네임은 노출해야함.


        return ResponseEntity.ok(mypageDto);
    }


    /**
     * 내 좋아요 가게 목록  UserController로 옮길것
     */
    @GetMapping("/storeLikeEdit")
    public ResponseEntity storeLikeList(Principal principal, Pageable pageable){
        String userId = principal.getName();
        Page<StoreLikesDto> storeLikeList = storeLikesService.myLikeList(userId, pageable);

        return ResponseEntity.ok(storeLikeList);
    }




    /**
     * 내 리뷰 리스트    userController로 옮길 예정
     */
    @GetMapping("/reviewList")
    public ResponseEntity myReviewList(Principal principal, Pageable pageable) {
        String userId = principal.getName();

        Page<ReviewDto> reviews = reviewService.myReviewList(userId, pageable);

        return ResponseEntity.ok(reviews);
    }





}
