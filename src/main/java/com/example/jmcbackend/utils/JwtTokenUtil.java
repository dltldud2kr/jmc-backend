package com.example.jmcbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class JwtTokenUtil {
    public static String createToken(String userName, String secretKey, long expireTimeMs) {
        Claims claims = Jwts.claims(); // 일종의 map
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 만든 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 끝나는 날짜
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
                ;
    }
}
