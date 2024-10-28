package com.example.spring_security.service;/*
 * @author -Suraj Tiwari
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtAuthService  {
    private static final String SECRET_KEY="413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    public String extractUserEmail(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T>claimsResolver){
       final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().
                parseClaimsJws(token).
                getBody();
    }

    private Key getSigningKey() {
        byte[]keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String, Object>extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();

    }
    
    public boolean isTokenValid(String token,UserDetails userDetails){
        String username=extractUserEmail(token);
        
        return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiredDate(token).before(new Date());
    }

    private Date extractExpiredDate(String token) {
    return extractClaim(token,Claims::getExpiration);
    }
}
