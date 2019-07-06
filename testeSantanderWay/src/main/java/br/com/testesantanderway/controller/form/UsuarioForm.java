package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UsuarioForm {
    @NotNull
    @NotEmpty
    @Length(min = 2)
    private String nomeUsuario;
    @NotNull @NotEmpty @Length(min = 11)
    private String email;
    @NotNull @NotEmpty @Length(min = 6)
    private String senha;
    private LocalDateTime dataCriacao;

    public UsuarioForm() {
    }

    public UsuarioForm(Usuario usuario) {
        this.nomeUsuario = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario converter() {
        return new Usuario(nomeUsuario, email, senha, dataCriacao);
    }
}
