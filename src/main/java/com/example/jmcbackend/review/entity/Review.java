package com.example.jmcbackend.review.entity;

import com.example.jmcbackend.member.entity.User;
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
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String reviewImg;
    private Float reviewScore;

    @Column(length = 1000)
    private String reviewText;

    private LocalDateTime reviewCreated;
    private LocalDateTime reviewUpdated;


}
