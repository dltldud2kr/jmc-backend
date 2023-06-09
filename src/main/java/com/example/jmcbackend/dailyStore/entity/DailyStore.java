package com.example.jmcbackend.dailyStore.entity;

import com.example.jmcbackend.regionFilter._enum.CityEnum;
import com.example.jmcbackend.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DailyStore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;



}
