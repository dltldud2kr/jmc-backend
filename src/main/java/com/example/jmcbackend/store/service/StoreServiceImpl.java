package com.example.jmcbackend.store.service;

import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final Trie trie;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final StoreLikeRepository storeLikeRepository;


    @Override
    public Store register(StoreInfoParam parameter, String userId) {

        Store byStoreName = storeRepository.findByStoreName(parameter.getStoreName());
        if (byStoreName.getStoreName().equals(parameter.getStoreName())){
            throw new IllegalStateException("존재하는 가게 명입니다.");
        }

        Store store = Store.builder()
                    .storeName(parameter.getStoreName())
                    .userId(userId)
                    .storeInfo(parameter.getStoreInfo())
                    .storeOpeningDateAndHours(parameter.getStoreOpeningDateAndHours())
                    .categoryId(parameter.getCategoryId())
                    .storeAddress(parameter.getStoreAddress())
                    .storeUrl(parameter.getStoreUrl())
                    .storePhone(parameter.getStorePhone())
                    .storeLikeCount(0L)
                    .storeReviewCount(0L)
                    .storeCreated(LocalDateTime.now())
                    .build();

            storeRepository.save(store);


        return store;
    }

    @Override
    public void storeDelete(StoreInfoParam parameter) {

        Optional<Store> store = storeRepository.findById(parameter.getStoreId());
        if(store.isPresent()){
            storeRepository.deleteById(parameter.getStoreId());
        } else {
            throw new IllegalStateException("가게 삭제 오류");
        }

    }

    @Override
    public List<Store> getAllStore() {

        return storeRepository.findAll();
    }

    @Override
    public StoreDto storeInfo(StoreInfoParam storeName) {

        Store byStoreName = storeRepository.findByStoreName(storeName.getStoreName());
        if(byStoreName == null) {
            throw new IllegalStateException("존재하지 않는 가게입니다.");
        }
        Optional<Store> optionalStore = storeRepository.findById(byStoreName.getStoreId());

        Store storeInfo = optionalStore.get();
        Long reviewCount = reviewRepository.countByStoreId(storeInfo.getStoreId());
        Long likeCount = storeLikeRepository.countByStoreId(storeInfo.getStoreId());


            StoreDto dto = StoreDto.builder()
                .storeId(storeInfo.getStoreId())
                .categoryId(storeInfo.getCategoryId())
                .storeInfo(storeInfo.getStoreInfo())
                .storeName(storeInfo.getStoreName())
                .storeAddress(storeInfo.getStoreAddress())
                .storeUrl(storeInfo.getStoreUrl())
                .storeReviewCount(reviewCount)
                .storeLikeCount(likeCount)
                .storePhone(storeInfo.getStorePhone())
                .storeOpeningDateAndHours(storeInfo.getStoreOpeningDateAndHours())
                .build();
        return dto;
    }

    @Override
    public List<Store> getCategoryStoreList(Long categoryId) {
        return storeRepository.findAllByCategoryId(categoryId);
    }

//    public void addAutoCompleteKeyword(String keyword) {
//        this.trie.put(keyword,null);
//    }
//
//    public List<String> autoComplete(String keyword) {
//        return (List<String>) this.trie.prefixMap(keyword).keySet()
//                .stream().collect(Collectors.toList());
//    }
//
//    public void deleteAutoCompleteKeyword(String keyword) {
//        this.trie.remove(keyword);
//    }


}
