package com.rejj.ecommerce.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;


@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);
    
        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(expirationDate).signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512).compact();
    
        return token;
    }

    public String getUsernameToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) throws Exception{

        try{
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token);
            return true;
        }catch(SignatureException ex){
            throw new Exception(ex.getMessage());
        }catch(MalformedJwtException ex){
            throw new Exception(ex.getMessage());
        }catch(ExpiredJwtException ex){
            throw new Exception(ex.getMessage());
        }catch(UnsupportedJwtException ex){
            throw new Exception(ex.getMessage());
        }catch(IllegalArgumentException ex){
            throw new Exception(ex.getMessage());
        }


    }
}
