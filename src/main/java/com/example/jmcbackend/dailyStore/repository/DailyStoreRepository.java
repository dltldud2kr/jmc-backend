package com.example.jmcbackend.dailyStore.repository;

import com.example.jmcbackend.dailyStore.entity.DailyStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStoreRepository extends JpaRepository<DailyStore, Long> {



}
