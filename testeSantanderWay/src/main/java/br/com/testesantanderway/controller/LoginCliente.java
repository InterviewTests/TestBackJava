package br.com.testesantanderway.controller;


import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.prefs.Preferences;

@RestController
public class LoginCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<ClienteDTO> dadosLoginCliente(String nome){
        if (nome == null) {
            Iterable<Cliente> clientes = clienteRepository.findAll();
            return ClienteDTO.converter(clientes);
        } else {
            Iterable<Cliente> clientes = clienteRepository.findByNome(nome);
            return ClienteDTO.converter(clientes);
        }
    }
//        List<Cliente> cliente = new ArrayList<>();
//        cliente.add(new Cliente(1L, "jonatas", "jonatas.santos@zup.com.br", "zupper"));
//        repository.saveAll(cliente);
//    }
}