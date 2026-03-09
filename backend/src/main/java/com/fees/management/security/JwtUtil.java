package com.fees.management.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "super-secret-key-super-secret-key-super-secret-key";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String email, String role, Long schoolId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("schoolId", schoolId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public static Long extractSchoolId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("schoolId", Long.class);
    }
}
