package com.example.jmcbackend.store.service;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface StoreService {

    /**
     * 가게 등록
     */
    Store register(StoreInfoParam parameter, String userId);

    /**
     * 가게 삭제
     */
    void storeDelete(StoreInfoParam parameter);


    /**
     * 가게 리스트
     */

    List<Store> getAllStore ();

    /**
     * 가게 관련 정보
     */
    StoreDto storeInfo(StoreInfoParam storeName);

    /**
     * 카테고리 리스트
     */

    List<Store> getCategoryStoreList(Long categoryId);




    /**
     * 자동완성
     * @param keyword
     */
//    void addAutoCompleteKeyword(String keyword);
//
//    List<String> autoComplete(String keyword);
//
//    void deleteAutoCompleteKeyword(String keyword);
//
//    void storeDelete(StoreInfoParam parameter);
}
