package com.example.jmcbackend.storeLike.controller;


import com.example.jmcbackend.storeLike.dto.StoreLikesDto;
import com.example.jmcbackend.storeLike.service.StoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class StoreLikesController {

    private final StoreLikesService storeLikesService;

    @PostMapping("/add/storeId/{storeId}")
    public ResponseEntity add(Principal principal ,@PathVariable("storeId") Long storeId) {

        String userId = principal.getName();
        ResponseEntity result = storeLikesService.add(userId, storeId);


        return ResponseEntity.ok(result);

    }

    /**
     * 사용자 좋아요 가게 목록
     */
    @GetMapping("/myLikeStore")
    public ResponseEntity myLikeList(Principal principal, Pageable pageable) {

        String userId = principal.getName();
        Page<StoreLikesDto> storeLikes = storeLikesService.myLikeList(userId, pageable);

        return ResponseEntity.ok(storeLikes);

    }


}
