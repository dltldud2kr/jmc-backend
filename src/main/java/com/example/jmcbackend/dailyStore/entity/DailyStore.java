package com.example.jmcbackend.dailyStore.entity;

import com.example.jmcbackend.store.entity.Store;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DailyStore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String img;


}
