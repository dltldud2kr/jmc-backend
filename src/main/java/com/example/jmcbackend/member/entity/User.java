package com.example.jmcbackend.member.entity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    private String userId;

//    @OneToMany(mappedBy = "user")
//    private List<StoreLikes> storeLikes = new ArrayList<>();

    private String userName;
    private String nickname;
    private String password;
    private LocalDateTime regDt;
//    private LocalDateTime udtDt;

}
