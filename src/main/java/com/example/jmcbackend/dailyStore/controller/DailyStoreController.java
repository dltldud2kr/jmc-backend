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
    public List<DailyStoreDto> top5(@RequestParam(value="count", defaultValue = "5") String countStr, Pageable pageable) {

        // defaultValue 값으로 받은 "5" 를 int 타입으로 변환
        int count = Integer.parseInt(countStr);

        List<DailyStoreDto> list = dailyStoreService.generateDailyStores(count,pageable);

        return list;
    }



}
