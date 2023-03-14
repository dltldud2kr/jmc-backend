package com.example.jmcbackend.category.repository;

import com.example.jmcbackend.category.entity.Category;
import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByCategoryName(String categoryName);

}
