package br.com.testesantanderway.controller;


import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class LoginCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    @ResponseBody
    public List<ClienteDTO> dadosLoginCliente(){
        Iterable<Cliente> clientes = clienteRepository.findAll();
        return ClienteDTO.converter(clientes);
    }
//        List<Cliente> cliente = new ArrayList<>();
//        cliente.add(new Cliente(1L, "jonatas", "jonatas.santos@zup.com.br", "zupper"));
//        repository.saveAll(cliente);
//    }

//   @GetMapping("/logincliente/{nome}")
//    public Cliente getClienteByName(@PathVariable String nome) {
//        return repository.findByNome(nome);
//    }
}