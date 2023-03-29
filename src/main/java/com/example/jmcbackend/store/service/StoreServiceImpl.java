package com.example.jmcbackend.store.service;

import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final StoreLikeRepository storeLikeRepository;


    @Override
    public Store register(StoreInfoParam parameter, String userId) {

        storeRepository.findByStoreName(parameter.getStoreName())
                .ifPresent(store -> {
                    throw new IllegalStateException("존재하는 가게 명입니다.");
                });

        Store store = Store.builder()
                    .storeName(parameter.getStoreName())
                    .userId(userId)
                    .storeIntroduction(parameter.getStoreIntroduction())
                    .storeOpeningDateAndHours(parameter.getStoreOpeningDateAndHours())
                    .categoryId(parameter.getCategoryId())
                    .storeAddress(parameter.getStoreAddress())
                    .storeUrl(parameter.getStoreUrl())
                    .storePhone(parameter.getStorePhone())
                    .storeCreated(LocalDateTime.now())
                    .build();

            storeRepository.save(store);

        return store;
    }

    @Override
    public void deleteStore(StoreInfoParam parameter) {

        Optional<Store> store = storeRepository.findById(parameter.getStoreId());
        store.ifPresentOrElse(storeRepository::delete,
                ()-> {
                    throw new IllegalStateException("가게 삭제 오류");
                });

    }

    @Override
    public List<StoreDto> getAllStore() {

        List<Store> storeList= storeRepository.findAll();
        List<StoreDto> list = of(storeList);

        return of(storeList);
    }


    @Override
    public StoreDto storeInfo(StoreInfoParam storeName) {

        Store storeInfo = storeRepository.findByStoreName(storeName.getStoreName())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 가게입니다."));

        Long reviewCount = reviewRepository.countByStoreId(storeInfo.getStoreId());
        Long likeCount = storeLikeRepository.countByStoreId(storeInfo.getStoreId());

        //리뷰점수 소수점 첫째자리까지
        Float reviewAvg = reviewRepository.reviewScoreAvg(storeInfo.getStoreId());

            StoreDto dto = StoreDto.builder()
                .storeId(storeInfo.getStoreId())
                .categoryId(storeInfo.getCategoryId())
                .storeIntroduction(storeInfo.getStoreIntroduction())
                .storeName(storeInfo.getStoreName())
                .storeAddress(storeInfo.getStoreAddress())
                .storeUrl(storeInfo.getStoreUrl())
                .storeReviewCount(reviewCount)
                .storeLikeCount(likeCount)
                    .reviewAvg(reviewAvg)
                .storePhone(storeInfo.getStorePhone())
                .storeOpeningDateAndHours(storeInfo.getStoreOpeningDateAndHours())
                .build();
        return dto;
    }

    @Override
    public List<Store> getCategoryStoreList(Long categoryId) {
        return storeRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Store> search(String keyword) {

        return storeRepository.findByStoreNameContaining(keyword);
    }





    public  List<StoreDto> of (List<Store> stores) {

        if (stores == null) {
            return null;
        }

        List<StoreDto> storeList = new ArrayList<>();
        for (Store x : stores) {
            storeList.add(of(x));
        }
        return storeList;

    }

    public  StoreDto of(Store store){

        Long reviewCount = reviewRepository.countByStoreId(store.getStoreId());
        Long likeCount = storeLikeRepository.countByStoreId(store.getStoreId());
        Float reviewAvg = reviewRepository.reviewScoreAvg(store.getStoreId());


        return StoreDto.builder()
                .storeId(store.getStoreId())
                .categoryId(store.getCategoryId())
                .storeIntroduction(store.getStoreIntroduction())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .storeUrl(store.getStoreUrl())
                .storeReviewCount(reviewCount)
                .storeLikeCount(likeCount)
                .reviewAvg(reviewAvg)
                .storePhone(store.getStorePhone())
                .storeOpeningDateAndHours(store.getStoreOpeningDateAndHours())
                .build();
    }


}
