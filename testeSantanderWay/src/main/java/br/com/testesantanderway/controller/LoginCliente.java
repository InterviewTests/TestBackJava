package br.com.testesantanderway.controller;


import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class LoginCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping("/clientes")
    //@PostConstruct
    public List<ClienteDTO> dadosLoginCliente(){
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

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