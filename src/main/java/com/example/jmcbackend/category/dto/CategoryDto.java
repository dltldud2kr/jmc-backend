package com.example.jmcbackend.category.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@JsonAutoDetect
public class CategoryDto {

    private String categoryName;
    private Long categoryId;
    private String box;
}
