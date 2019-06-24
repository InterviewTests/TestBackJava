package br.com.testesantanderway.testeSantanderWay.controlle;

import modelo.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class LoginCliente {
    @RequestMapping("/logincliente")
    @ResponseBody
    public Cliente login(){
        Cliente cliente = new Cliente(1L, "jonatas", "jonatas.santos@zup.com.br", "zupper");

        return cliente;
    }
}
