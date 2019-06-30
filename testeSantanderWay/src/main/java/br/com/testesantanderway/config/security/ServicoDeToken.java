package br.com.testesantanderway.config.security;

import br.com.testesantanderway.modelo.Cliente;
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
        Cliente clienteLogado = (Cliente) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiracao));

        return Jwts.builder()
                .setIssuer("Api teste way")
                .setSubject(clienteLogado.getCodigoUsuario())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}