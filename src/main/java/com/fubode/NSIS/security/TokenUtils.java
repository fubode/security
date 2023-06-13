package com.fubode.NSIS.security;

import com.fubode.NSIS.model.UsuarioRol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.*;

public class TokenUtils {
    //private final static String ACCESS_TOKEN_SECRET = "201400076FUBODE";
    private final static Key ACCESS_TOKEN_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String codUsuario, String usuario, Integer idPersona, Integer idUsuario, List<UsuarioRol> usuarioRoles){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS*1_000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);
        Map<String,Object> extra = new HashMap<>();

        extra.put("cod_usuario",codUsuario);
        extra.put("id_persona",idPersona);
        extra.put("id_usuario",idUsuario);
        extra.put("usuario",usuario);
        extra.put("roles",usuarioRoles);
        extra.put("roles","usuarioRoles");

        return Jwts.builder()
                .setSubject(usuario)
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
