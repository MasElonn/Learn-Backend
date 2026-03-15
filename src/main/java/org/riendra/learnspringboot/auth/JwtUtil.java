package org.riendra.learnspringboot.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor(
            "Learning-Backend-Development-404".getBytes()
    );
    private final long EXPIRY = 1000 * 60 * 15;

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().verifyWith((javax.crypto.SecretKey) key)
                .build().parseSignedClaims(token).getPayload().getSubject();

    }
    public boolean isValid(String token){
        try{
            extractUsername(token);
            return true;
        } catch(JwtException e){
            return false;
        }
    }
}
