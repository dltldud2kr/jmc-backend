package com.example.jmcbackend.mainFront.controller;

import com.example.jmcbackend.category.service.CategoryService;
import com.example.jmcbackend.mainFront.dto.MainDto;
import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.service.StoreService;
import com.example.jmcbackend.storeLike.repository.StoreLikesRepository;
import com.example.jmcbackend.storeLike.service.StoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestaurantFinderController {

    private final StoreService storeService;
    private final ReviewService reviewService;
    private final StoreLikesService storeLikesService;
    private final ReviewRepository reviewRepository;
    private final CategoryService categoryService;
    private final StoreLikesRepository storeLikesRepository;


    /**
     * 나중에 StoreController에 합칠 예정.
     * @param pageable
     * @return
     */

    @GetMapping("/store")
    public ResponseEntity<Page<MainDto>> mainStore(Pageable pageable){

        Page<StoreDto> allStore = storeService.getAllStore(pageable);

        Page<MainDto> mainDtos = allStore.map(store ->{
//            Long reviewCount = reviewRepository.countByStore(store);
//            Float reviewAvg = reviewRepository.reviewScoreAvg(store.getStoreId());
            Long likeCount = storeLikesRepository.countByStoreId(store.getStoreId());

            return MainDto.builder()
                    .storeId(store.getStoreId())
                    .categoryId(store.getCategoryId())
                    .contactNumber(store.getContactNumber())
//                    .thumbnailImg()
                    .openTime(store.getOpenTime())
                    .introduction(store.getIntroduction())
                    .userId(store.getUserId())
                    .regionCode(store.getRegionCode())
                    .storeName(store.getStoreName())
                    .address(store.getAddress())
                    .storeCreated(store.getStoreCreated())
                    .storeUpdated(store.getStoreUpdated())
                    .storeLikeCount(likeCount)
//                    .reviewAvg(reviewAvg)
//                    .storeReviewCount(reviewCount)
                    .build();
        });

        return ResponseEntity.ok(mainDtos);
    }



}
