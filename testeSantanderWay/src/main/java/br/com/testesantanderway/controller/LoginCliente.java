package controlle;

import dto.ClienteDTO;
import modelo.Cliente;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginCliente {
    @RequestMapping("/logincliente")
    public ClienteDTO dadosLoginCliente(){
        Cliente cliente = new Cliente(1L, "jonatas", "jonatas.santos@zup.com.br", "zupper");

        return ClienteDTO.converter(cliente);
    }
}