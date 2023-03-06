package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import org.springframework.stereotype.Service;

@Service
public interface StoreLikeService {
    /**
     * 좋아요 누른 가게
     */
    StoreLike add(String userId, Long storeId);
}
