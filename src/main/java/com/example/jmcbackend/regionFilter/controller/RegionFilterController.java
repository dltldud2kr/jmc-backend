package com.example.jmcbackend.regionFilter.controller;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.regionFilter._enum.EnumMapperValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region")

/**
 * @title 지역별 상점 분리를 위한 컨트롤러
 * @auther 최성우
 * @update 23.5.26
 */
@Controller
public class RegionFilterController {
//    private final ResionFilterService resionFilterService;

    /**
     * @title 현재 등록되어있는 regionCategory를 반환합니다.
     * @return
     */
    @GetMapping
    public List<EnumMapperValue> region() {
        return Arrays.stream(CityEnum.values())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }
}
