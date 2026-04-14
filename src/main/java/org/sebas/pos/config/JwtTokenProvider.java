package org.sebas.pos.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private String jwtSecret = generateSecretKey();
    private final Long EXPIRATION_DATE  = 3600000L; // 1h = 3600s 3600 *100 360000 miliseconds

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + EXPIRATION_DATE);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

    }

    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //generate key
    private SecretKey key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //validate JWT token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith(key())
                .build()
                .parse(token);
        return true;
    }

    public String generateSecretKey(){
        // length means (32 bytes are required for 256-bit key)
        int lenght = 32;

        SecureRandom secureRandom = new SecureRandom();

        //Create a secure random generator
        byte[] keyBytes = new byte[lenght];

        secureRandom.nextBytes(keyBytes);

        return Base64.getEncoder().encodeToString(keyBytes);

    }
}
