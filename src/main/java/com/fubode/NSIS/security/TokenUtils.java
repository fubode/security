package com.fubode.NSIS.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.xml.crypto.Data;
import java.security.Key;
import java.util.*;

public class TokenUtils {
    //private final static String ACCESS_TOKEN_SECRET = "201400076FUBODE";
    private final static Key ACCESS_TOKEN_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String nombre, String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS*1_000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);
        Map<String,Object> extra = new HashMap<>();
        extra.put("nombre",nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(ACCESS_TOKEN_SECRET)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET)
                    .build()
                    .parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}
