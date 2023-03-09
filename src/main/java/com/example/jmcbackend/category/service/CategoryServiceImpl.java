package com.example.jmcbackend.category.service;

import com.example.jmcbackend.category.dto.CategoryDto;
import com.example.jmcbackend.category.entity.Category;
import com.example.jmcbackend.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.web.authentication.RequestMatcherDelegatingAuthenticationManagerResolver.builder;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category add(CategoryDto dto) {

        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(dto.getCategoryName());
        if(optionalCategory.isPresent()){
            throw new IllegalStateException("같은 카테고리가 존재합니다.");
        } else {

            Category category = Category.builder()
                    .categoryName(dto.getCategoryName())
                    .build();
            categoryRepository.save(category);


            return category;
        }
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
