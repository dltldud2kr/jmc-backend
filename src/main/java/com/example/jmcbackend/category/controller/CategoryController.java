package com.example.jmcbackend.category.controller;

import com.example.jmcbackend.category.dto.CategoryDto;
import com.example.jmcbackend.category.entity.Category;
import com.example.jmcbackend.category.service.CategoryService;
import com.example.jmcbackend.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody CategoryDto dto) {

        Category result = categoryService.add(dto);

        return ResponseEntity.ok(result);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> categoryList(){

        List<CategoryDto> allCategories = categoryService.getAllCategories();

        return ResponseEntity.ok(allCategories);
    }


}
