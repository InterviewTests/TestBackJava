package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

    private String nome;

    private String email;

    public ClienteDTO(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public static ClienteDTO converter(Cliente cliente){
        return new ClienteDTO(cliente.getNome(), cliente.getEmail());
    }

    public static List<ClienteDTO> converter(Iterable<Cliente> clientes) {
        List<ClienteDTO> list = new ArrayList();
        for (Cliente cliente : clientes) {
            list.add(ClienteDTO.converter(cliente));
        }
        return list;
    }
}