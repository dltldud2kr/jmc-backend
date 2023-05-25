package com.example.jmcbackend.dailyStore.controller;


import com.example.jmcbackend.dailyStore.dto.DailyStoreDto;
import com.example.jmcbackend.dailyStore.service.DailyStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dailyStore")
public class DailyStoreController {

    private final DailyStoreService dailyStoreService;

    @GetMapping
    public List<DailyStoreDto> top5(Pageable pageable) {

        List<DailyStoreDto> list = dailyStoreService.generateDailyStores(pageable);


        return list;
    }



}
