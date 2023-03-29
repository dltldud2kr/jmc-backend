package com.example.jmcbackend.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.jmcbackend.review.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Long countByStoreId(Long storeId);

    Page<Review> findAllByUserId(String userId, Pageable pageable);

    Page<Review> findAllByStoreId(Long userId, Pageable pageable);




    /**
     * 소수 첫째자리 까지 리뷰 평균 , null값일시 0 반환
     */
    @Query(value = "select COALESCE(ROUND(AVG(r.reviewScore), 1), 0) from Review r where r.storeId = :storename ")
    Float reviewScoreAvg(@Param("storename") Long storeId);
}
