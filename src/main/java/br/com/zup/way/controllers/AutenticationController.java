package br.com.zup.way.controllers;

import br.com.zup.way.db.postgres.model.dto.LoginDTO;
import br.com.zup.way.db.postgres.model.dto.TokenDTO;
import br.com.zup.way.service.security.JWTTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    private AuthenticationManager authenticationManager;
    private JWTTokenService tokenService;

    public AutenticationController(AuthenticationManager authenticationManager, JWTTokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginDTO login) {
        UsernamePasswordAuthenticationToken userNameToken = login.converter();
        Authentication authentication = authenticationManager.authenticate(userNameToken);
        return ResponseEntity.ok(new TokenDTO(tokenService.generateToken(authentication), "Bearer"));
    }
}
