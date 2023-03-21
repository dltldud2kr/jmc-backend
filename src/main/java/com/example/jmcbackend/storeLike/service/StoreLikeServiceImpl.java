package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreLikeServiceImpl implements StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;

    private final StoreRepository storeRepository;

    /**
     *  좋아요를 한 번 더 눌렀을 때 비활성화되게 설정하기
     */
    @Override
    public ResponseEntity add(String userId, Long storeId) {

        Optional<Store> optionalStoreId = storeRepository.findById(storeId);

        if (!optionalStoreId.isPresent()) {
            throw new IllegalStateException("가게가 존재하지 않습니다.");
        }
        Optional<StoreLike> byUserIdAndStoreId = storeLikeRepository.findByUserIdAndStoreId(userId, storeId);


        if(!byUserIdAndStoreId.isPresent()) {
            log.info("좋아요누른 가게가 중복 x ");

            StoreLike storeLike = StoreLike.builder()
                    .storeId(storeId)
                    .userId(userId)
                    .isActive(true)
                    .build();
            storeLikeRepository.save(storeLike);

            return ResponseEntity.ok(storeLike);

        } else {

            storeLikeRepository.deleteById(byUserIdAndStoreId.get().getId());
            log.info("좋아요누른 가게가 전에 눌렀던 가게라면 좋아요를 풀기위해 삭제가 성공 ");

            return ResponseEntity.ok("좋아요 목록에서 제거");
        }

    }

    @Override
    public List<StoreLike> myLikeList(String userId) {

        return storeLikeRepository.findAllByUserId(userId);
    }

}
