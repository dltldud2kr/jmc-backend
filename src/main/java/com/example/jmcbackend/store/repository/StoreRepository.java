package com.example.jmcbackend.store.repository;

import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByStoreName(String storeName);

    List<Store> findAllByCategoryId(Long categoryId);

    List<Store> findByStoreNameContaining(String storeName);

    Page<Store> findAll(Pageable pageable);



}
