package com.example.jmcbackend.storeLike.controller;


import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.service.StoreLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class StoreLikeController {

    private final StoreLikeService storeLikeService;

    @PostMapping("/add/storeId/{store}")
    public ResponseEntity add(Principal principal ,@PathVariable("store") Long storeId) {

        String userId = principal.getName();
        StoreLike result = storeLikeService.add(userId, storeId);


        return ResponseEntity.ok(result);

    }


}
