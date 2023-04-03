package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.MypageDto;
import com.example.jmcbackend.member.dto.StoreEditDto;
import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.service.UserService;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.service.StoreService;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.service.StoreLikeService;
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
    private final StoreLikeService storeLikeService;

    @GetMapping
    public ResponseEntity getMyPage(Principal principal, Pageable pageable){
        String userId = principal.getName();

//        List<StoreDto> storeList = storeService.myStoreList(userId);
        Page<ReviewDto> reviewList = reviewService.myReviewList(userId,pageable);
        Page<StoreLikeDto> storeLikeList = storeLikeService.myLikeList(userId, pageable);
        UserDto userDto = userService.myInfo(userId);

        MypageDto mypageDto = new MypageDto();
//        mypageDto.setStoreList(storeList);
        mypageDto.setReviewList(reviewList);
        mypageDto.setStoreLikeList(storeLikeList);
        mypageDto.setUser(userDto); //자기 정보는 다른 페이지 드가야 뜨게해야할듯함. 닉네임은 노출해야함.


        return ResponseEntity.ok(mypageDto);
    }

    @GetMapping("/storeEdit")
    public ResponseEntity myStoreList(Principal principal, Pageable pageable){
        String userId = principal.getName();
        Page<StoreLikeDto> storeLikeList = storeLikeService.myLikeList(userId, pageable);

        return ResponseEntity.ok(storeLikeList);
    }

    @PostMapping("/{storeId}/storeEdit")
    public ResponseEntity storeEdit(@PathVariable("storeId") Long storeId, Principal principal, @RequestBody StoreEditDto dto){
        String userId = principal.getName();

        ResponseEntity result = storeService.modify(userId,storeId,dto);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{storeId}/storeEdit")
    public ResponseEntity storeDelete (@PathVariable("storeId") Long storeId ,Principal principal) {
        String userId = principal.getName();

        storeService.deleteStore(storeId,userId);

        return ResponseEntity.ok("삭제완료");
    }


    @GetMapping("/reviewEdit")
    public ResponseEntity myReviewList(Principal principal, Pageable pageable) {
        String userId = principal.getName();

        Page<ReviewDto> reviews = reviewService.myReviewList(userId, pageable);

        return ResponseEntity.ok(reviews);
    }
    @PostMapping("/{reviewId}/reviewEdit")
    public ResponseEntity reviewEdit(@PathVariable("reviewId")Long reviewId, Principal principal, @RequestBody ReviewDto dto) {
        String userId = principal.getName();

        ResponseEntity result = reviewService.modify(userId,reviewId,dto);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{reviewId}/reviewEdit")
    public ResponseEntity delReview(Principal principal, @PathVariable("reviewId") Long reviewId) {

        String userId = principal.getName();
        ResponseEntity result = reviewService.del(reviewId,userId);

        return ResponseEntity.ok(result);
    }
    @PostMapping("/userEdit")
        public ResponseEntity userInfoEdit( Principal principal, @RequestBody UserDto dto) {
            String userId = principal.getName();

            ResponseEntity result = userService.modify(userId,dto);

            return ResponseEntity.ok(result);
        }
//    @PostMapping("/{storeLikeId}/likeEdit")


}
