package br.com.testesantanderway.controller;

import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
public class LoginCliente {
    @RequestMapping("/logincliente")
    @PostConstruct
    public ClienteDTO dadosLoginCliente(){
        Cliente cliente = new Cliente(1L, "jonatas", "jonatas.santos@zup.com.br", "zupper");

        return ClienteDTO.converter(cliente);
    }
}