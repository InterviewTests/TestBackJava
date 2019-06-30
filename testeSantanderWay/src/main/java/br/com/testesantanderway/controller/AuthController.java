package br.com.testesantanderway.controller;

import br.com.testesantanderway.config.security.ServicoDeToken;
import br.com.testesantanderway.controller.form.AuthForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private ServicoDeToken servicoDeToken;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = servicoDeToken.gerarToken(authentication);
            System.out.println(token);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }
}