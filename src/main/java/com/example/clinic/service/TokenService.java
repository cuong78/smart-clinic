package com.example.clinic.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {
    private final SecretKey key;

    public TokenService(@Value("${app.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)  // Thay setSubject() bằng subject()
                .issuedAt(new Date())  // Thay setIssuedAt() bằng issuedAt()
                .expiration(new Date(System.currentTimeMillis() + 1000L*60*60))  // Thay setExpiration() bằng expiration()
                .signWith(key)  // Không cần chỉ định SignatureAlgorithm nữa
                .compact();
    }

    public SecretKey getSigningKey() {
        return key;
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()  // Thay parserBuilder() bằng parser()
                    .verifyWith(key)  // Thay setSigningKey() bằng verifyWith()
                    .build()
                    .parseSignedClaims(token);  // Thay parseClaimsJws() bằng parseSignedClaims()
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String subject(String token) {
        return Jwts.parser()  // Thay parserBuilder() bằng parser()
                .verifyWith(key)  // Thay setSigningKey() bằng verifyWith()
                .build()
                .parseSignedClaims(token)  // Thay parseClaimsJws() bằng parseSignedClaims()
                .getPayload()  // Thay getBody() bằng getPayload()
                .getSubject();
    }
}