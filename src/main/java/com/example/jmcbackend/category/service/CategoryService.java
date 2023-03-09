package com.example.jmcbackend.category.service;

import com.example.jmcbackend.category.dto.CategoryDto;
import com.example.jmcbackend.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    /**
     * 카테고리 추가
     */
    Category add (CategoryDto dto);

    /**
     * 카테고리 리스트
     */
    Page<Category> getAllCategories (Pageable pageable);
}
