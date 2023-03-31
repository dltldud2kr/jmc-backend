package com.example.jmcbackend.member.dto;

import com.example.jmcbackend.review.dto.ReviewDto;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MypageDto {

    private List<StoreDto> storeList;
    private Page<ReviewDto> reviewList;
    private Page<StoreLikeDto> storeLikeList;
    private UserDto user;
}
