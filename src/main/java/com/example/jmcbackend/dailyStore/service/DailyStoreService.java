package com.example.jmcbackend.dailyStore.service;

import com.example.jmcbackend.dailyStore.dto.DailyStoreDto;
import com.example.jmcbackend.dailyStore.entity.DailyStore;
import com.example.jmcbackend.dailyStore.repository.DailyStoreRepository;
import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.repository.StoreLikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyStoreService {

    private final DailyStoreRepository dailyStoreRepository;
    private final ReviewRepository reviewRepository;
    private final StoreLikesRepository storeLikesRepository;
    private final StoreRepository storeRepository;

//    리뷰와 좋아요 개수를 기준으로 지정 count 수대로 가게(Store)를 반환합니다.
//    @Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행되도록 설정
    public List<DailyStoreDto> generateDailyStores(int count, Pageable pageable) {
        // 기존 추천 음식점 데이터 삭제
        dailyStoreRepository.deleteAll();

        log.info(String.valueOf(count));

        // 새로운 추천 음식점 생성 및 저장 로직 구현  -> storeRepository @Query 참고
        Page<Store> result = storeRepository.findTop5StoresWithReviewAndLikeCounts(PageRequest.of(0,count));
        List<DailyStore> dailyStores = new ArrayList<>();
        List<DailyStoreDto> dto = new ArrayList<>();

        result.getContent().forEach(store -> {
            Long reviewCount = reviewRepository.countByStore(store);
            Float reviewAvg = reviewRepository.reviewScoreAvg(store);
            Long likeCount = storeLikesRepository.countByStoreId(store.getStoreId());

            // Store 엔티티를 조회하여 설정
            Store fetchedStore = storeRepository.findById(store.getStoreId()).orElse(null);
            if (fetchedStore != null) {
                DailyStore dailyStore = new DailyStore();
                dailyStore.setStore(store);

                dailyStores.add(dailyStore);
            }

            DailyStoreDto dailyStoreDto = DailyStoreDto.builder()
                    .storeId(fetchedStore.getStoreId())
                    .storeName(fetchedStore.getStoreName())
                    .storeReviewCount(reviewCount)
                    .reviewAvg(reviewAvg)
                    .storeLikeCount(likeCount)
                    .categoryId(fetchedStore.getCategoryId())
                    .address(fetchedStore.getAddress())
                    .userId(fetchedStore.getUserId())
                    .openTime(fetchedStore.getOpenTime())
                    .storeCreated(fetchedStore.getStoreCreated())
                    .storeUpdated(fetchedStore.getStoreUpdated())
                    .introduction(fetchedStore.getIntroduction())
                    .regionCode(fetchedStore.getRegionCode())
                    .thumbnailImg(fetchedStore.getThumbnailImg())
                    .build();
            dto.add(dailyStoreDto);
        });

        dailyStoreRepository.saveAll(dailyStores); // 일괄 저장

        return dto;
    }



}
