package com.example.jmcbackend.dailyStore;

import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.review.service.ReviewService;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.repository.StoreLikesRepository;
import com.example.jmcbackend.storeLike.service.StoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyStoreService {

    private final DailyStoreRepository dailyStoreRepository;
    private final ReviewRepository reviewRepository;
    private final StoreLikesRepository storeLikesRepository;
    private final StoreRepository storeRepository;

//    @Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행되도록 설정
    public List<DailyStoreDto> generateDailyStores(Pageable pageable) {
        // 기존 추천 음식점 데이터 삭제
        dailyStoreRepository.deleteAll();

        // 새로운 추천 음식점 생성 및 저장 로직 구현
        Page<Store> result = storeRepository.findTop5StoresWithReviewAndLikeCounts(PageRequest.of(0,5));
        List<DailyStore> dailyStores = new ArrayList<>();
        List<DailyStoreDto> dto = new ArrayList<>();

        result.getContent().forEach(store -> {
            Long reviewCount = reviewRepository.countByStoreId(store.getStoreId());
            Float reviewAvg = reviewRepository.reviewScoreAvg(store.getStoreId());
            Long likeCount = storeLikesRepository.countByStoreId(store.getStoreId());

            // Store 엔티티를 조회하여 설정
            Store fetchedStore = storeRepository.findById(store.getStoreId()).orElse(null);
            if (fetchedStore != null) {
                DailyStore dailyStore = new DailyStore();
                dailyStore.setStoreName(fetchedStore.getStoreName());
                dailyStore.setStore(fetchedStore);
                dailyStores.add(dailyStore);
            }

            DailyStoreDto dailyStoreDto = DailyStoreDto.builder()
                    .storeName(fetchedStore.getStoreName())
                    .reviewCount(reviewCount)
                    .reviewAvg(reviewAvg)
                    .likeCount(likeCount)
                    .build();
            dto.add(dailyStoreDto);
        });

        dailyStoreRepository.saveAll(dailyStores); // 일괄 저장

        return dto;
    }

    public ResponseEntity test (DailyStoreDto dto) {
        DailyStore dailyStore = new DailyStore();

        dailyStore.setStoreName(dto.getStoreName());

        dailyStoreRepository.save(dailyStore);


        return ResponseEntity.ok(dailyStore);
    }

}
