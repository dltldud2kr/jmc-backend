package com.example.jmcbackend.member.repository;


import com.example.jmcbackend.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

}
