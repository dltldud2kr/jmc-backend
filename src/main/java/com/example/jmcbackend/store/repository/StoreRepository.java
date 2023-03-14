package com.example.jmcbackend.store.repository;

import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreName(String storeName);

    List<Store> findAllByCategoryId(Long categoryId);

}
