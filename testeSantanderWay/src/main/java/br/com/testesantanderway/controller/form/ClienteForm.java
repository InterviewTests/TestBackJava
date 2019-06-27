package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ClienteForm {
    @NotNull @NotEmpty @Length(min = 25)
    private String email;
    @NotNull @NotEmpty @Length(min = 10)
    private String senha;

    public ClienteForm() {
    }

    public ClienteForm(Cliente cliente){
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
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

    public Cliente converter() {
        return new Cliente(email, senha);
    }
}