package com.example.jmcbackend.store.entity;

import com.example.jmcbackend.category.entity.Category;
import com.example.jmcbackend.storeLike.entity.StoreLike;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String storeName;

    private Long categoryId;

    private String storePhone;
    private String storeAddress;
    private String storeUrl;


    private String storeOpeningDateAndHours;    //
    private String storeIntroduction;

    private String userId;

    private LocalDateTime storeCreated;
    private LocalDateTime storeUpdated;


}
