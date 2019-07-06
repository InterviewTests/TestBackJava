package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;
import java.time.LocalDateTime;

public class DetalheClienteDTO {
    private String nomeCliente;
    private String email;
    private LocalDateTime dataCriacao;

    public DetalheClienteDTO(Cliente cliente) {
        this.nomeCliente = cliente.getNomeCliente();
        this.email = cliente.getEmail();
        this.dataCriacao = cliente.getDataCriacao();
    }

    public String getNome() {
        return nomeCliente;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDateTime() {
        return dataCriacao;
    }
}