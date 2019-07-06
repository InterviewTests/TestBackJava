package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Sistema;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServicoDeToken {
    @Value("${testeSantanderWay.jwt.expiration}")
    private String expiracao;
    @Value("${testeSantanderWay.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Sistema sistemaLogado = (Sistema) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiracao));

        return Jwts.builder()
                .setIssuer("Api teste way")
                .setSubject(sistemaLogado.getCodigo())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String token){
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String getCodigo(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}