package com.example.jmcbackend.store.controller;

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
    @GetMapping("/{storeId}/info")
    public ResponseEntity storeInfo (@PathVariable("storeId") Long storeId) {

        StoreDto storeDto = storeService.storeInfo(storeId);


        return ResponseEntity.ok(storeDto);
    }


    /**
     * 지정 카테고리 가게 목록
     */
    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity categoryStoreList(@PathVariable("categoryId") Long categoryId){

        List<Store> stores = storeService.getCategoryStoreList(categoryId);

        return ResponseEntity.ok(stores);
    }


    @GetMapping("/search")
    public ResponseEntity searchStoreList(@RequestParam String keyword) {

        List<Store> search = storeService.search(keyword);

        return ResponseEntity.ok(search);
    }

    @GetMapping("/{storeId}/reviewList")
    public ResponseEntity storeReviewList(@PathVariable("storeId") Long storeId, Pageable pageable) {

        Page<ReviewDto> reviews = reviewService.storeReviewList(storeId, pageable);

        return ResponseEntity.ok(reviews);
    }

}
