package com.example.jmcbackend.storeLike.repository;


import com.example.jmcbackend.storeLike.entity.StoreLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {

    Long countByStoreId(Long storeId);

    List<StoreLike> findAllByUserId(String userId);
}
