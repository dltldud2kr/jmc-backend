package com.example.jmcbackend.store.repository;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreSimpleListRes;
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

    Page<Store> findAllByRegionCode(CityEnum regionCode, Pageable pageable);


    //리뷰 개수 순으로 스토어 불러오기
//    @Query("SELECT s FROM Store s LEFT JOIN s.storeId r GROUP BY s.storeId ORDER BY COUNT(r) DESC")
//    Page<Store> findAllStoreReviewCount(Pageable pageable);

//    //리뷰 평점 순으로 스토어 불러오기
//    @Query
//    Page<Store> findAllStoreReviewCount(Pageable pageable);
//
//    //좋아요 개수 순으로
//    @Query
//    Page<Store> findAlldkdkdkd(Pageable pageable);

    // 리뷰와 좋아요 개수를 기준으로 상위 5개의 가게(Store)를 반환합니다.
    @Query("SELECT s " +
            "FROM Store s " +
            "LEFT JOIN Review r ON s.storeId = r.storeId " +
            "LEFT JOIN StoreLikes sl ON s.storeId = sl.storeId " +
            "GROUP BY s.storeId " +
            "ORDER BY (COUNT(r.reviewId) + COUNT(sl.id)) DESC")
    Page<Store> findTop5StoresWithReviewAndLikeCounts(Pageable pageable);
}
