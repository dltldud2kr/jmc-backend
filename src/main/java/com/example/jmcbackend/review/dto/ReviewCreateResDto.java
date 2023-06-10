package com.example.jmcbackend.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewCreateResDto {

    private Long reviewId;
    private Float reviewScore;
    private String reviewText;
    private String reviewImg;
    private Long storeId;

}



