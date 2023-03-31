package com.example.jmcbackend.storeLike.controller;


import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.service.StoreLikeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class StoreLikeController {

    private final StoreLikeService storeLikeService;

    @PostMapping("/add/storeId/{store}")
    public ResponseEntity add(Principal principal ,@PathVariable("store") Long storeId) {

        String userId = principal.getName();
        ResponseEntity result = storeLikeService.add(userId, storeId);


        return ResponseEntity.ok(result);

    }

    @GetMapping("/myLikeStore")
    public ResponseEntity myLikeList(Principal principal, Pageable pageable) {

        String userId = principal.getName();
        Page<StoreLikeDto> storeLikes =storeLikeService.myLikeList(userId, pageable);

        return ResponseEntity.ok(storeLikes);

    }


}
