package com.santander.test.backend.bweninger.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static String key = "";

    public static final String TOKEN_HEADER = "Authentication";

    public static String create(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static Jws<Claims> decode(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }

    public static String getCpf(String token) {
        Jws<Claims> claims = JwtUtil.decode(token);
        return claims.getBody().getSubject();
    }

    @Value("${secret.key}")
    public void setKeyString(String keyString) {
         key = keyString;
    }
}
