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

    private Long storeId; //스토어 아이디



}
