package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreLikeServiceImpl implements StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;

    private final StoreRepository storeRepository;

    /**
     *  좋아요를 한 번 더 눌렀을 때 비활성화되게 설정하기

     */
    @Override
    public StoreLike add( String userId, Long storeId) {

        Optional<Store> optionalStoreId = storeRepository.findById(storeId);

        if (!optionalStoreId.isPresent()) {
            throw new IllegalStateException("가게가 존재하지 않습니다.");
        } else {
            StoreLike storeLike = StoreLike.builder()
                    .storeId(storeId)
                    .userId(userId)
                    .build();
            storeLikeRepository.save(storeLike);

            return storeLike;
        }
    }

    @Override
    public List<StoreLike> myLikeList(String userId) {

        return storeLikeRepository.findAllByUserId(userId);
    }

}
