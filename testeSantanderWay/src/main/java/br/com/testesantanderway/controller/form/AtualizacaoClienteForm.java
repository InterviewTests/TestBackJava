package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Cliente;
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

    public AtualizacaoClienteForm(Cliente cliente) {
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

    public Cliente atualizar(String id, ClienteRepository clienteRepository) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));

        cliente.setEmail(this.email);
        cliente.setSenha(this.senha);

        clienteRepository.save(cliente);

        return cliente;
    }
}