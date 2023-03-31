package com.example.jmcbackend.storeLike.dto;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreLikeDto {

    private Long storeId;

    private String userId;

    public static StoreLikeDto of(StoreLike storeLike) {
        StoreLikeDto storeLikeDto = new StoreLikeDto();
        storeLikeDto.setStoreId(storeLike.getStoreId());
        storeLikeDto.setUserId(storeLike.getUserId());
        return storeLikeDto;
    }
}
