package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private String nomeUsuario;
    private String email;
    private LocalDateTime dataCriacao;

    public UsuarioDTO(Usuario usuario) {
        this.nomeUsuario = usuario.getNomeUsuario();
        this.email = usuario.getEmail();
        this.dataCriacao = usuario.getDataCriacao();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDTO::new);
    }
}