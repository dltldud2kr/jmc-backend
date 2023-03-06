package com.example.jmcbackend.storeLike.service;

import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.dto.StoreLikeDto;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import com.example.jmcbackend.storeLike.repository.StoreLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreLikeServiceImpl implements StoreLikeService {

    private final StoreLikeRepository storeLikeRepository;

    private final StoreRepository storeRepository;

    /**
     *  좋아요를 한 번 더 눌렀을 때 비활성화되게 설정하기
     *  일단 외래키 2개로 설정하고 값을 넣으니 storeId와 userId에 오류가 생김... 이유 모름 ㅠ
     */
    @Override
    public StoreLike add( String userId, Long storeId) {

//        Optional<Store> optionalStoreId = storeRepository.findById(storeId);
//
//        if(!optionalStoreId.isPresent()) {
//            throw new IllegalStateException("가게가 존재하지 않습니다.");
//        } else {
//            StoreLike storeLike = StoreLike.builder()
//                    .store(new Store(storeId))
//                    .user(new User(userId))
//                    .build();
//            storeLikeRepository.save(storeLike);
//
//            return storeLike;
        return null;
        }

}
