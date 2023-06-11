package com.example.jmcbackend.store.service;

import com.example.jmcbackend.exception.AppException;
import com.example.jmcbackend.exception.ErrorCode;
import com.example.jmcbackend.member.dto.StoreEditDto;
import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.member.repository.UserRepository;
import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.review.repository.ReviewRepository;
import com.example.jmcbackend.store.dto.StoreDto;
import com.example.jmcbackend.store.dto.StoreInfoParam;
import com.example.jmcbackend.store.dto.StoreSimpleListRes;
import com.example.jmcbackend.store.entity.Store;
import com.example.jmcbackend.store.repository.StoreRepository;
import com.example.jmcbackend.storeLike.repository.StoreLikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {
    private final UserRepository userRepository;

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final StoreLikesRepository storeLikesRepository;


    @Override
    public Store register(StoreInfoParam parameter,  String userId) {

        storeRepository.findByStoreName(parameter.getStoreName())
                .ifPresent(store -> {
                    throw new IllegalStateException("존재하는 가게 명입니다.");
                });

//        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));

        log.info(String.valueOf("리전코드정보: " + parameter.getRegionCode()));

        Store store = Store.builder()
                    .storeName(parameter.getStoreName())
                    .userId(userId)
                    .introduction(parameter.getIntroduction())
                    .openTime(parameter.getOpenTime())
                    .categoryId(parameter.getCategoryId())
                    .address(parameter.getAddress())
                    .regionCode(parameter.getRegionCode())
                    .url(parameter.getUrl())
                    .phone(parameter.getPhone())
                    .thumbnailImg(parameter.getThumbnailImg())
                    .storeCreated(LocalDateTime.now())
                    .build();

        storeRepository.save(store);

        return store;
    }

    @Override
    public ResponseEntity deleteStore(Long storeId, String userId) {
        Optional<Store> store = storeRepository.findById(storeId);

        if (store.isPresent() && store.get().getUserId().equals(userId)) {
            storeRepository.deleteById(storeId);
            return ResponseEntity.ok().body("삭제완료");
        } else {
            throw new IllegalStateException("가게 삭제 오류");
        }
    }

    @Override
    public Page<StoreDto> getAllStore(Pageable pageable) {

        Page<Store> storePage= storeRepository.findAll(pageable);
        List<StoreDto> storeDtoList = of(storePage.getContent());

        return new PageImpl<>(storeDtoList, pageable, storePage.getTotalElements());
    }



    @Override
    public StoreDto storeInfo(Long storeId) {

        Store storeInfo = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 가게입니다."));

        Long likeCount = storeLikesRepository.countByStoreId(storeId);
        Long reviewCount = reviewRepository.countByStore(storeInfo);

        //리뷰점수 소수점 첫째자리까지
        Float reviewAvg = reviewRepository.reviewScoreAvg(storeInfo);

            StoreDto dto = StoreDto.builder()
                .storeId(storeInfo.getStoreId())
                .categoryId(storeInfo.getCategoryId())
                .introduction(storeInfo.getIntroduction())
                .storeName(storeInfo.getStoreName())
                .address(storeInfo.getAddress())
                .url(storeInfo.getUrl())
//                .storeReviewCount((long) storeInfo.getReviews().size())   // 리뷰 개수가 많을 경우 성능 이슈
                .storeReviewCount(reviewCount)
                .storeLikeCount(likeCount)
                .reviewAvg(reviewAvg)
                .regionCode(storeInfo.getRegionCode())
                .contactNumber(storeInfo.getPhone())
                .openTime(storeInfo.getOpenTime())
                .build();
        return dto;
    }

    @Override
    public List<StoreDto> myStoreList(String userId) {

        List<Store> myStoreList = storeRepository.findByUserId(userId);
        if (myStoreList.isEmpty()){
            throw new AppException(ErrorCode.STORE_NOT_FOUND,"Cannot found your store.");
        }

        List<StoreDto> storeDtoList = of(myStoreList);
        return storeDtoList;
    }

    @Override
    public List<Store> getCategoryStoreList(Long categoryId) {
        return storeRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<StoreSimpleListRes> getRegionStoreList(String regionCode, Pageable pageable) {
        try{
            Page<Store> storeList;

            if(regionCode.equals("all")) {
                storeList = storeRepository.findAll(pageable);
            } else {
                storeList = storeRepository.findAllByRegionCode(CityEnum.valueOf(regionCode.toString()),pageable);
            }
                //반활할 return DTO LIST
                List<StoreSimpleListRes> returnDto = new ArrayList<>();
                StoreSimpleListRes storeListDto = new StoreSimpleListRes();

                for (Store store : storeList) {
                    System.out.println("store : " + store.toString());
                    long reviewCount = reviewRepository.countByStore(store);
                    Float reviewAvg = reviewRepository.reviewScoreAvg(store);
                    long likeCount = storeLikesRepository.countByStoreId(store.getStoreId());

                    returnDto.add(storeListDto.fromEntity(store,reviewCount,reviewAvg,likeCount));
                }
                return returnDto;
        } catch (Exception e) {
            return null;
        }
    }


/*
    @Override
    public List<StoreSimpleListRes> getRegionStoreList1(String regionCode, int count, int loadingType) {
        CityEnum cityEnum = CityEnum.fromCode(regionCode);  // String 으로 받은 regionCode 값을 CityEnum 으로 변환 후
                                                            // 비어있거나, 일치하는 값이 없을 시 null 값을 반환.


        // 비어있거나 일치하는 값이 없을 시에는 전체 리스트를 불러오게 해둠
        if (regionCode.equalsIgnoreCase("all") ||  cityEnum == null) {
            Page<Store> regionAllStore = storeRepository.findAll(pageable);
            List<StoreDto> allStoreDtoList = of(regionAllStore.getContent());

            return new PageImpl<>(allStoreDtoList, pageable, regionAllStore.getTotalElements());

        } else {
            Page<Store> regionFilterStore = storeRepository.findAllByRegionCode(CityEnum.valueOf(regionCode), pageable);
            List<StoreDto> regionStoreDtoList = of(regionFilterStore.getContent());

            return new PageImpl<>(regionStoreDtoList, pageable, regionFilterStore.getTotalElements());
        }
    }
*/

    @Override
    public List<Store> search(String keyword) {

        return storeRepository.findByStoreNameContaining(keyword);
    }

    @Override
    public ResponseEntity modify(String userId, Long storeId, StoreEditDto dto) {

        log.info("가게가있는지 찾아봄.");
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NoSuchElementException("Cannot find store with storeId" + storeId));

        log.info("유저아이디와 가게작성자 아이디를 비교함.");
        if (!store.getUserId().equals(userId)) {
            throw new AppException(ErrorCode.UN_AUTHORIZED, "You do not have permission to modify this store.");
        }
        log.info("dto로 받은 값을 entity로 옮김");
            store.setStoreName(dto.getStoreName());
            store.setUrl(dto.getUrl());
            store.setAddress(dto.getAddress());
            store.setPhone(dto.getPhone());
            store.setIntroduction(dto.getIntroduction());
            store.setOpenTime(dto.getOpenTime());
            store.setCategoryId(dto.getCategoryId());
            store.setStoreUpdated(LocalDateTime.now());

            log.info("repository에 저장함.");
            storeRepository.save(store);

        System.out.println(store.getStoreName());

            return ResponseEntity.ok().body("수정완료");
        }




    public  List<StoreDto> of (List<Store> stores) {

        if (stores == null) {
            return null;
        }

        List<StoreDto> storeList = new ArrayList<>();
        for (Store x : stores) {
            storeList.add(of(x));
        }
        return storeList;

    }

    public  StoreDto of(Store store){

        Long reviewCount = reviewRepository.countByStore(store);
        Long likeCount = storeLikesRepository.countByStoreId(store.getStoreId());
        Float reviewAvg = reviewRepository.reviewScoreAvg(store);


        return StoreDto.builder()
                .storeId(store.getStoreId())
                .userId(store.getUserId())
                .regionCode(store.getRegionCode())
                .categoryId(store.getCategoryId())
                .introduction(store.getIntroduction())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .storeReviewCount(reviewCount)
                .storeLikeCount(likeCount)
                .thumbnailImg(store.getThumbnailImg())
                .reviewAvg(reviewAvg)
                .storeUpdated(store.getStoreUpdated())
                .storeCreated(store.getStoreCreated())
                .contactNumber(store.getPhone())
                .openTime(store.getOpenTime())
                .build();
    }




}
