package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Usuario;
import br.com.testesantanderway.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoUsuarioForm {
@NotNull
@NotEmpty
@Length(min = 11)
private String email;
    @NotNull @NotEmpty @Length(min = 6)
    private String senha;

    public AtualizacaoUsuarioForm() {
    }

    public AtualizacaoUsuarioForm(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
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

    public Usuario atualizar(String id, UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));

        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);

        usuarioRepository.save(usuario);

        return usuario;
    }
}