package com.example.jmcbackend.member.entity;

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
public class User {
    @Id
    private String userId;

//    @OneToMany(mappedBy = "user")
//    private List<StoreLike> storeLikes = new ArrayList<>();

    private String userName;
    private String userNickname;
    private String password;
    private LocalDateTime regDt;

}
