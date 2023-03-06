package com.example.jmcbackend.storeLike.controller;


import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.service.StoreLikeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class StoreLikeController {

    private final StoreLikeService storeLikeService;

    @PostMapping("/add/{store}")
    public ResponseEntity add(   Principal principal ,@PathVariable("store") Long storeId) {

        String userId = principal.getName();
            StoreLike result = storeLikeService.add(userId, storeId);


        return ResponseEntity.ok(result);

    }

    @PostMapping("/{test}")
    public ResponseEntity tests (@PathVariable("test") String test){
        return ResponseEntity.ok(test);
    }
}
