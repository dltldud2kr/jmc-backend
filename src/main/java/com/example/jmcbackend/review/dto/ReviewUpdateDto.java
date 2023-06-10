package com.example.jmcbackend.review.dto;

import com.example.jmcbackend.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewUpdateDto {

    private Float reviewScore;
    private String reviewText;
    private String storeName;
    private String reviewImg;
    private LocalDateTime reviewCreated;
    private LocalDateTime reviewUpdated;

}



