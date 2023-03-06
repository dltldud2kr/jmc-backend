package com.example.jmcbackend.store.service;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {

    /**
     * 매장 등록
     */
    Store register(StoreInfoParam parameter, String userId);

    /**
     * 매장 리스트
     */

    Page<Store> getAllStore (Pageable pageable);
}
