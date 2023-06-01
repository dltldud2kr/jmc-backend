package com.example.jmcbackend.regionFilter._enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//dd
@Getter
@AllArgsConstructor
public class EnumMapperFactory {
    private Map<String, List<EnumMapperValue>> factory;

    // 새로운 Enum 종류를 추가하는 함수
    public void put(String key, Class<? extends EnumMapperType> e) {
        factory.put(key, toEnumValues(e));
    }

    // 특정 Enum의 항목들을 조회하는 함수
    public List<EnumMapperValue> get(String key) {
        return factory.get(key);
    }

    // Enum의 내용들을 List로 바꾸어주는 함수
    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e) {
        return Arrays.stream(e.getEnumConstants()).map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }
}
