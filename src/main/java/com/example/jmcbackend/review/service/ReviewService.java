package com.example.jmcbackend.review.service;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    Review add(ReviewDto dto, String userId, Long storeId);
}
