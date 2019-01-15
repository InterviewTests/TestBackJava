package com.santander.test.backend.bweninger.vo;

import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
public class UsuarioVO {

    private String email;
    private String nome;
    private String apelido;
    private String senha;
    private String repeteSenha;
    private String token;
    private String msg;
    private String cpf;

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepeteSenha() {
        return repeteSenha;
    }

    public void setRepeteSenha(String repeteSenha) {
        this.repeteSenha = repeteSenha;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
