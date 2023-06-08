package com.example.jmcbackend.store.service;
import com.example.jmcbackend.member.dto.StoreEditDto;
import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.dto.StoreSimpleListRes;
import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    /**
     * 가게 등록
     */
    Store register(StoreInfoParam parameter,  String userId);

    /**
     * 가게 삭제
     */
    ResponseEntity deleteStore(Long storeId, String userId);


    /**
     * 가게 리스트
     */

    Page<StoreDto> getAllStore (Pageable pageable);



    /**
     * 가게 관련 정보
     */
    StoreDto storeInfo(Long storeId);

    /**
     * 내 가게 리스트
     */
    List<StoreDto> myStoreList(String userId);

    /**
     * 카테고리 리스트
     */

    List<Store> getCategoryStoreList(Long categoryId);


    /**
     *  리전 카테고리 리스트 (시영)
     */
    Page<StoreDto> getRegionStoreList(String regionCode, Pageable pageable);

    /**
     * 가게 검색 기능
     */
    List<Store> search(String keyword);

    /**
     * 가게 정보 수정
     */
    ResponseEntity modify(String userId, Long storeId, StoreEditDto storeDto);



}
