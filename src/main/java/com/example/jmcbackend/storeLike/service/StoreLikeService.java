package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreLikeService {
    /**
     * 좋아요 누른 가게
     */
    ResponseEntity add(String userId, Long storeId);

    /**
     * 내 좋아요 가게 리스트
     * @param userId
     * @return
     */
    List<StoreLike> myLikeList(String userId);
}
