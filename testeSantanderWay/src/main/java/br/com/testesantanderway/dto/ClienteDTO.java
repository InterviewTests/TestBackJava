package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Cliente;

import java.util.List;

public class ClienteDTO {
    private String nome;
    private String email;

    public ClienteDTO(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public static List<ClienteDTO> converter(List<Cliente> cliente) {
        return new ClienteDTO(cliente.getNome(), cliente.getEmail());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}