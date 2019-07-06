package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ClienteDTO {
    private String nomeCliente;
    private String email;
    private LocalDateTime dataCriacao;

    public ClienteDTO(Cliente cliente){
        this.nomeCliente = cliente.getNomeCliente();
        this.email = cliente.getEmail();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEmail() {
        return email;
    }

    public static Page<ClienteDTO> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDTO::new);
    }
}