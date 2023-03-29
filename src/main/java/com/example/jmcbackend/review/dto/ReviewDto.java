package com.example.jmcbackend.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ReviewDto {

    private Float reviewScore;
    private String reviewText;
    private String storeName;

}
