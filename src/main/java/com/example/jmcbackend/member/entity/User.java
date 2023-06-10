package com.example.jmcbackend.member.entity;

import com.example.jmcbackend.review.entity.Review;
import com.example.jmcbackend.store.entity.Store;
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
public class User {
    @Id
    private String userId;

    @OneToMany(mappedBy = "user")
    List<Review> reviews = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    List<Store> stores = new ArrayList<>();

    private String userName;
    private String nickname;
    private String password;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

}
