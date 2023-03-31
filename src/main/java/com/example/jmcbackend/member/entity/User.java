package com.example.jmcbackend.member.entity;

import com.example.jmcbackend.storeLike.entity.StoreLike;
import lombok.*;
import net.bytebuddy.asm.Advice;


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

//    @OneToMany(mappedBy = "user")
//    private List<StoreLike> storeLikes = new ArrayList<>();

    private String userName;
    private String userNickname;
    private String password;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

}
