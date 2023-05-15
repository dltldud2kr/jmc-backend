package com.example.jmcbackend.store.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    private String openTime;    //
    private String introduction;
    private String userId;

    private LocalDateTime storeCreated;
    private LocalDateTime storeUpdated;


}
