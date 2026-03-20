package com.munir.crud_pessoa.security.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.munir.crud_pessoa.dtos.response.TokenResponseDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
    private String secret;
	
	private final String PERFIS = "perfis";

    private Key getSignKey() {
    	
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public TokenResponseDTO generateToken(UserDetails user) {
    	
    	Date issuedAt = new Date();
    	Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim(PERFIS, user.getAuthorities())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
        
        return new TokenResponseDTO(token, issuedAt, expiration);
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
    	
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}