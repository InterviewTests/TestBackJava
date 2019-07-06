package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Sistema;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ClienteForm {
    @NotNull @NotEmpty @Length(min = 2)
    private String nomeCliente;
    @NotNull @NotEmpty @Length(min = 11)
    private String email;
    @NotNull @NotEmpty @Length(min = 6)
    private String senha;
    private LocalDateTime dataCriacao;

    public ClienteForm() {
    }

    public ClienteForm(Sistema sistema){
        this.nomeCliente = sistema.getNome();
        this.email = sistema.getEmail();
        this.senha = sistema.getSenha();
        this.dataCriacao = sistema.getDataCriacao();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    public void LocalDateTime (LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Sistema converter() {
        return new Sistema(nomeCliente, email, senha, dataCriacao);
    }
}