package com.example.jmcbackend.store.repository;

import com.example.jmcbackend.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreName(String storeName);

}
