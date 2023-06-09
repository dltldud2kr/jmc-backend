package com.example.jmcbackend.category.service;

import com.example.jmcbackend.category.dto.CategoryDto;
import com.example.jmcbackend.category.entity.Category;
import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    /**
     * 카테고리 추가
     */
    Category add (CategoryDto dto);

    /**
     * 카테고리 리스트
     */
    List<CategoryDto> getAllCategories ();

    /**
     * 카테고리 삭제 ( 사용자 권한을 부여하지 않은 삭제 )
     */

    ResponseEntity delete (Long categoryId);

}
