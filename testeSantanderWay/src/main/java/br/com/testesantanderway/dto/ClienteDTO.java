package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ClienteDTO {
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;

    public ClienteDTO(Cliente cliente){
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public static Page<ClienteDTO> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDTO::new);
    }
}