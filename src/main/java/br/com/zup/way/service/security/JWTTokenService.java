package br.com.zup.way.service.security;

import br.com.zup.way.db.postgres.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTTokenService {

    @Value("${way.jwt.expiration}")
    private String expiration;

    @Value("${way.jwt.secret}")
    private String secret;


    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setIssuer("API Way")
                .setSubject(((Usuario) authentication.getPrincipal()).getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addMilliseconds(new Date(), Integer.valueOf(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            if (token != null) {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                return true;
            }
        } catch (SignatureException sign) {
            return false;
        }
        return false;
    }

    public Long getIdByToken(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
    }
}
