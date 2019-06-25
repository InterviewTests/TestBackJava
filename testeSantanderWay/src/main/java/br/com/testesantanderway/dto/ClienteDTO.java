package dto;

import modelo.Cliente;

public class ClienteDTO {
    private String nome;
    private String email;

    public ClienteDTO(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public static ClienteDTO converter(Cliente cliente) {
        return new ClienteDTO(cliente.getNome(), cliente.getEmail());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}