package com.example.hotel.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.hotel.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTUtils {
    
    private static final long  EXPIRATION_TIME=1000 * 60 * 24 * 7;// for 7 DAYS

    private final SecretKey key;

    public JWTUtils(){
        String secretString="45412454215455751245425454sdf548df454542454515s75f4s54";
        byte[] keyBytes=Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.key=new SecretKeySpec(keyBytes,"HmacSHA256");

    }

    public String genrateToken(User userdetails){
        return Jwts.builder()
            .subject(userdetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
            .signWith(key )
            .compact();
    }

    public String extractUsername(String token){
        return extractClaims(token ,Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims , T> claimsFunction){
        return claimsFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isValidToken(String token ,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token, Claims :: getExpiration).before(new Date());
    }

}
