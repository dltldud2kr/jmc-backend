package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            throw new IllegalStateException("The store does not exist.");
        }
        Optional<StoreLike> byUserIdAndStoreId = storeLikeRepository.findByUserIdAndStoreId(userId, storeId);


        StoreLike storeLike = StoreLike.builder()
                .storeId(storeId)
                .userId(userId)
                .build();
        if(!byUserIdAndStoreId.isPresent()) {
            log.info("좋아요누른 가게가 중복 x ");
            storeLike.setIsActive(true);
            storeLikeRepository.save(storeLike);

            return ResponseEntity.ok(storeLike);

        } else {

            storeLike.setIsActive(false);
            storeLikeRepository.deleteById(byUserIdAndStoreId.get().getId());
            log.info("좋아요누른 가게가 전에 눌렀던 가게라면 좋아요를 풀기위해 삭제가 성공 ");

            return ResponseEntity.ok("Cancel the likes");
        }
    }

    @Override
    public Page<StoreLikeDto> myLikeList(String userId, Pageable pageable) {

        Page<StoreLike> storeLikes = storeLikeRepository.findAllByUserId(userId, pageable);
        Page<StoreLikeDto> storeLikeList = storeLikes.map(StoreLikeDto::of);
        return storeLikeList;
    }

}
