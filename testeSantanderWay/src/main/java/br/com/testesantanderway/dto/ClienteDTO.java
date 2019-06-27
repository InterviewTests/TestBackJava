package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ClienteDTO {

    private String nome;

    private String email;

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

    public static List<ClienteDTO> converter(Iterable<Cliente> clientes) {
        return StreamSupport.stream(clientes.spliterator(),false).map(ClienteDTO::new).collect(Collectors.toList());
    }
}