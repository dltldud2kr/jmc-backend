package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.storeLike.dto.StoreLikesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StoreLikesService {
    /**
     * 좋아요 누른 가게
     */
    ResponseEntity add(String userId, Long storeId);

    /**
     * 내 좋아요 가게 리스트
     * @param userId
     * @return
     */
    Page<StoreLikesDto> myLikeList(String userId, Pageable pageable);
}
