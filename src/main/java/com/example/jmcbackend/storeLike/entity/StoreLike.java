package com.example.jmcbackend.storeLike.entity;

import com.example.jmcbackend.member.entity.User;
import com.example.jmcbackend.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StoreLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
