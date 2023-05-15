package com.example.jmcbackend.store.controller;

import com.example.jmcbackend.member.dto.StoreEditDto;
import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final ReviewService reviewService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody StoreInfoParam parameter ,Principal principal) {

            String userId = principal.getName();
            Store result = storeService.register(parameter,userId);


        return  ResponseEntity.ok(result);

    }


    @GetMapping("/list")
    public ResponseEntity list (Pageable pageable){

        Page<StoreDto> stores =storeService.getAllStore(pageable);

        return ResponseEntity.ok(stores);
    }

    /**
     * 가게 클릭시 가게 세부정보
     */
    @GetMapping("/{storeId}/info")
    public ResponseEntity storeInfo (@PathVariable("storeId") Long storeId) {

        StoreDto storeDto = storeService.storeInfo(storeId);


        return ResponseEntity.ok(storeDto);
    }


    /**
     * 지정 카테고리 가게 목록    카테고리Controller로 옮길것
     */
    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity categoryStoreList(@PathVariable("categoryId") Long categoryId){

        List<Store> stores = storeService.getCategoryStoreList(categoryId);

        return ResponseEntity.ok(stores);
    }

    /**
     * 가게 검색기능
     */

    @GetMapping("/search")
    public ResponseEntity searchStoreList(@RequestParam String keyword) {

        List<Store> search = storeService.search(keyword);

        return ResponseEntity.ok(search);
    }

    /**
     * 특정 가게 리뷰 리스트
     * @param storeId
     * @param pageable
     * @return
     */
    @GetMapping("/{storeId}/reviewList")
    public ResponseEntity storeReviewList(@PathVariable("storeId") Long storeId, Pageable pageable) {

        Page<ReviewDto> reviews = reviewService.storeReviewList(storeId, pageable);

        return ResponseEntity.ok(reviews);
    }


    /**
     * 사용자 등록 가게 리스트
     */

    @GetMapping("/storeEdit")
    public ResponseEntity myStoreList(Principal principal){

        String userId = principal.getName();
        List<StoreDto> storeList = storeService.myStoreList(userId);

        return ResponseEntity.ok(storeList);
    }




    /**
     * 사용자가 등록한 가게 수정
     */
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
}
