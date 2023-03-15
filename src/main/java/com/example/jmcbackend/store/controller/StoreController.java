package com.example.jmcbackend.store.controller;

import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody StoreInfoParam parameter ,Principal principal) {

            String userId = principal.getName();
            Store result = storeService.register(parameter,userId);
            // 가게가 저장될 때 마다 Trie 에도 가게명이 저장
//            this.storeService.addAutoCompleteKeyword(result.getStoreName());

        return  ResponseEntity.ok(result);

    }

    //한국어를 인식하지 않음.

//    @GetMapping("/autocomplete")
//    public ResponseEntity autoComplete(@RequestParam String keyword){
//        var result = this.storeService.autoComplete(keyword);
//        return ResponseEntity.ok(result);
//    }

    @PostMapping("/list")
    public ResponseEntity list (){

        List<Store> stores =storeService.getAllStore();

        return ResponseEntity.ok(stores);
    }

    @PostMapping("/info")
    public ResponseEntity storeInfo (@RequestBody StoreInfoParam storeName) {

        StoreDto storeDto = storeService.storeInfo(storeName);

        return ResponseEntity.ok(storeDto);
    }

    @PostMapping("/delete")
    public ResponseEntity storeDelete (@RequestBody StoreInfoParam parameter) {

        storeService.storeDelete(parameter);

        return ResponseEntity.ok("삭제완료");
    }

    /**
     * 지정 카테고리 가게 목록
     */
    @PostMapping("/categoryId/{categoryId}")
    public ResponseEntity categoryStoreList(@PathVariable("categoryId") Long categoryId){

        List<Store> stores = storeService.getCategoryStoreList(categoryId);

        return ResponseEntity.ok(stores);
    }

}
