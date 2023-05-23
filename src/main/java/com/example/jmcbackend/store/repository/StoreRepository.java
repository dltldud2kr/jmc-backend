package com.example.jmcbackend.store.repository;

import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByStoreName(String storeName);

    List<Store> findAllByCategoryId(Long categoryId);

    List<Store> findByStoreNameContaining(String storeName);

    Page<Store> findAll(Pageable pageable);

    List<Store> findByUserId(String userId);


    @Query("SELECT s " +
            "FROM Store s " +
            "LEFT JOIN Review r ON s.storeId = r.storeId " +
            "LEFT JOIN StoreLikes sl ON s.storeId = sl.storeId " +
            "GROUP BY s.storeId " +
            "ORDER BY (COUNT(r.reviewId) + COUNT(sl.id)) DESC")
    Page<Store> findTop5StoresWithReviewAndLikeCounts(Pageable pageable);
}
