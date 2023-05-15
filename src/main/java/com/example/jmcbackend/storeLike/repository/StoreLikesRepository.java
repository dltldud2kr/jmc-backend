package com.example.jmcbackend.storeLike.repository;


import com.example.jmcbackend.storeLike.entity.StoreLikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreLikesRepository extends JpaRepository<StoreLikes, Long> {

    Long countByStoreId(Long storeId);

    Page<StoreLikes> findAllByUserId(String userId, Pageable pageable);

    Optional<StoreLikes> findByUserIdAndStoreId(String userId, Long storeId);

    void deleteByUserIdAndStoreId(String userId, Long storeId);
}
