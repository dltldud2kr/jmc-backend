package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.dto.StoreLikesDto;
import com.example.jmcbackend.storeLike.entity.StoreLikes;
import com.example.jmcbackend.storeLike.repository.StoreLikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreLikesServiceImpl implements StoreLikesService {

    private final StoreLikesRepository storeLikesRepository;

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
        Optional<StoreLikes> byUserIdAndStoreId = storeLikesRepository.findByUserIdAndStoreId(userId, storeId);


        StoreLikes storeLikes = StoreLikes.builder()
                .storeId(storeId)
                .userId(userId)
                .build();
        if(!byUserIdAndStoreId.isPresent()) {
            log.info("좋아요누른 가게가 중복 x ");
            storeLikes.setIsActive(true);
            storeLikesRepository.save(storeLikes);

            return ResponseEntity.ok(storeLikes);

        } else {

            storeLikes.setIsActive(false);
            storeLikesRepository.deleteById(byUserIdAndStoreId.get().getId());
            log.info("좋아요누른 가게가 전에 눌렀던 가게라면 좋아요를 풀기위해 삭제가 성공 ");

            return ResponseEntity.ok("Cancel the likes");
        }
    }

    @Override
    public Page<StoreLikesDto> myLikeList(String userId, Pageable pageable) {

        Page<StoreLikes> storeLikes = storeLikesRepository.findAllByUserId(userId, pageable);
        Page<StoreLikesDto> storeLikeList = storeLikes.map(StoreLikesDto::of);
        return storeLikeList;
    }

}
