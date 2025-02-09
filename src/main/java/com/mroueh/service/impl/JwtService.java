package com.mroueh.service.impl;

import com.mroueh.exception.SessionExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secret_key;

    @Value("${application.security.jwt.expiration}")
    private Long expiration;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
        public String generateToken(String username, String roles) {
            return Jwts.builder()
                    .subject(username)
                    .claim("roles", roles)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 hour
                    .signWith(getSignInKey())
                    .compact();
        }

        public Claims extractClaims(String token) {
        try{
            return Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        }catch(Exception e){
            throw new SessionExpiredException("Session expired");
        }
        }

        public boolean isTokenValid(String token, String username) {
            String extractedUsername = extractClaims(token).getSubject();
            return extractedUsername.equals(username) && !isTokenExpired(token);
        }
    public boolean validateToken(String token) {
        try {
            // Parse token and ensure it is valid
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new SessionExpiredException("session expired");
        }
    }

        private boolean isTokenExpired(String token) {
            return extractClaims(token).getExpiration().before(new Date());
        }
        public String getRoleFromToken(String token) {
            Claims claims = extractClaims(token);
            return (String) claims.get("roles");
        }
        public String getUsername(String token) {
            Claims claims = extractClaims(token);
            return claims.getSubject();
        }
    }



