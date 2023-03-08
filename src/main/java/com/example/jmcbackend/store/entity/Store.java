package com.example.jmcbackend.store.entity;

import com.example.jmcbackend.category.entity.Category;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String storeName;

//    @ManyToOne
//    @JoinColumn(name="category_id")
//    private Category category;

    private Long storeCategory;

    private Long storeLikeCount;
    private Long storeReviewCount;

    private String storePhone;
    private String storeAddress;
    private String storeUrl;

//    @OneToMany(mappedBy = "store")
//    private List<StoreLike> storeLikes = new ArrayList<>();

    private String storeOpeningDateAndHours;    //
    private String storeInfo;

    private String userId;

    private LocalDateTime storeCreated;
    private LocalDateTime storeUpdated;


}
