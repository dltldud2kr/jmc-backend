package com.example.jmcbackend.store.service;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    /**
     * 가게 등록
     */
    Store register(StoreInfoParam parameter, String userId);

    /**
     * 가게 삭제
     */
    void deleteStore(StoreInfoParam parameter);


    /**
     * 가게 리스트
     */

    Page<StoreDto> getAllStore (Pageable pageable);

    /**
     * 가게 관련 정보
     */
    StoreDto storeInfo(StoreInfoParam storeName);

    /**
     * 내 가게 리스트
     */
    List<StoreDto> myStoreList(String userId);

    /**
     * 카테고리 리스트
     */

    List<Store> getCategoryStoreList(Long categoryId);

    /**
     * 가게 검색 기능
     */
    List<Store> search(String keyword);

    /**
     * 가게 정보 수정
     */
    void modify(String userId, Long storeId, StoreDto storeDto);



}
