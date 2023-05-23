package com.example.jmcbackend.configuration;
import java.util.HashSet;
import java.util.Set;

public class JwtBlacklist {


    private static Set<String> tokenBlacklist = new HashSet<>();

    public static void addToBlacklist(String token) {
        tokenBlacklist.add(token);
    }

    public static boolean isBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }

    public static Set<String> getBlacklist() {
        return tokenBlacklist;
    }

    public static void invalidateToken(String token) {
        addToBlacklist(token);
    }
}