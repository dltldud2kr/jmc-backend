package com.example.jmcbackend.store.entity;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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



    private String phone;
    private String address;
    private String url;

    @Column
    @Enumerated(EnumType.STRING)
    private CityEnum regionEnum;

    private String openTime;
    private String introduction;
    private String userId;

    private LocalDateTime storeCreated;
    private LocalDateTime storeUpdated;




}
