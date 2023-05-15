package com.example.jmcbackend.storeLike.dto;

import com.example.jmcbackend.storeLike.entity.StoreLikes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreLikesDto {

    private Long storeId;

    private String userId;

    public static StoreLikesDto of(StoreLikes storeLikes) {
        StoreLikesDto storeLikesDto = new StoreLikesDto();
        storeLikesDto.setStoreId(storeLikes.getStoreId());
        storeLikesDto.setUserId(storeLikes.getUserId());
        return storeLikesDto;
    }
}
