package com.example.jmcbackend.store.controller;

import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody StoreInfoParam parameter ,Principal principal) {

            String userId = principal.getName();
            Store result = storeService.register(parameter,userId);

        return  ResponseEntity.ok(result);

    }

    @PostMapping("/list")
    public ResponseEntity list (Pageable pageable){

        Page<Store> stores =storeService.getAllStore(pageable);
        return ResponseEntity.ok(stores);
    }

}
