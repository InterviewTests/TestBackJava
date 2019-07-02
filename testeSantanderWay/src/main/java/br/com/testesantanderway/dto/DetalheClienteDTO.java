package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;
import java.time.LocalDateTime;

public class DetalheClienteDTO {
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;

    public DetalheClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.dataCriacao = cliente.getDataCriacao();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDateTime() {
        return dataCriacao;
    }
}