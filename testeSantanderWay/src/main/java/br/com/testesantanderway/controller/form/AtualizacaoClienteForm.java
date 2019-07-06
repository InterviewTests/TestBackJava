package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Sistema;
import br.com.testesantanderway.repository.ClienteRepository;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoClienteForm {
    @NotNull @NotEmpty @Length(min = 11)
    private String email;
    @NotNull @NotEmpty @Length(min = 6)
    private String senha;

    public AtualizacaoClienteForm() {
    }

    public AtualizacaoClienteForm(Sistema sistema) {
        this.email = sistema.getEmail();
        this.senha = sistema.getSenha();
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

    public Sistema atualizar(String id, ClienteRepository clienteRepository) {
        Sistema sistema = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));

        sistema.setEmail(this.email);
        sistema.setSenha(this.senha);

        clienteRepository.save(sistema);

        return sistema;
    }
}