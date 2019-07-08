package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.modelo.Sistema;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServicoDeToken {
    public static String USUARIO_TESTE = "12615a2f-92a3-4bfd-a6f3-5a352b65438b";

    @Value("${testeSantanderWay.jwt.expiration}")
    private String expiracao;
    @Value("${testeSantanderWay.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication, String issuer) {
        Object principal = authentication.getPrincipal();
        String subject = null;
        if(principal instanceof Sistema){
            Sistema sistema = (Sistema) principal;
            subject = sistema.getCodigo();
        }else if (principal instanceof Cliente){
            Cliente cliente = (Cliente) principal;
            subject = cliente.getCodigo();
        }
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiracao));

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCodigo(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}