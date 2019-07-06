package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Sistema;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ClienteDTO {
    private String nomeCliente;
    private String email;
    private LocalDateTime dataCriacao;

    public ClienteDTO(Sistema sistema){
        this.nomeCliente = sistema.getNome();
        this.email = sistema.getEmail();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEmail() {
        return email;
    }

    public static Page<ClienteDTO> converter(Page<Sistema> clientes) {
        return clientes.map(ClienteDTO::new);
    }
}