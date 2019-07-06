package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Usuario;

import java.time.LocalDateTime;

public class DetalheUsuarioDTO {
    private String nomeUsuario;
    private String email;
    private LocalDateTime dataCriacao;

    public DetalheUsuarioDTO(Usuario usuario) {
        this.nomeUsuario = usuario.getNomeUsuario();
        this.email = usuario.getEmail();
        this.dataCriacao = usuario.getDataCriacao();
    }

    public String getNome() {
        return nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDateTime() {
        return dataCriacao;
    }
}

