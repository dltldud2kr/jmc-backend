package com.example.jmcbackend.category.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@JsonAutoDetect
public class CategoryDto {

    private String categoryName;
    private String box;
}
