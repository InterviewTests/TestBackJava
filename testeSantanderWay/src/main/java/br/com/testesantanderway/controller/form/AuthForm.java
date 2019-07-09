package br.com.testesantanderway.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Arrays;

public class AuthForm {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken criarAutenticacaoUsuario() {
        return new UsernamePasswordAuthenticationToken(email, senha, Arrays.asList(() -> "USUARIO"));
    }

    public UsernamePasswordAuthenticationToken ciarAutenticacaoSistema() {
        return new UsernamePasswordAuthenticationToken(email, senha, Arrays.asList(() -> "SISTEMA"));
    }
}