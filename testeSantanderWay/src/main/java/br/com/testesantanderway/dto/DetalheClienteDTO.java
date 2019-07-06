package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Sistema;
import java.time.LocalDateTime;

public class DetalheClienteDTO {
    private String nomeCliente;
    private String email;
    private LocalDateTime dataCriacao;

    public DetalheClienteDTO(Sistema sistema) {
        this.nomeCliente = sistema.getNome();
        this.email = sistema.getEmail();
        this.dataCriacao = sistema.getDataCriacao();
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