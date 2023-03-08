package com.example.jmcbackend.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ReviewDto {

    private Byte reviewScore;
    private String reviewText;

}
