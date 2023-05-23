package com.example.jmcbackend.dailyStore;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/test")
    public ResponseEntity test (@RequestBody DailyStoreDto dto) {

        ResponseEntity responseEntity = dailyStoreService.test(dto);

    return responseEntity;
    }
}
