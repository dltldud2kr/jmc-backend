package com.example.jmcbackend.store.service;

import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;


    @Override
    public Store register(StoreInfoParam parameter, String userId) {


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
    public Page<Store> getAllStore(Pageable pageable) {

        return storeRepository.findAll(pageable);
    }
}
