package br.com.testesantanderway.controller;


import br.com.testesantanderway.controller.form.ClienteForm;
import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.dto.DetalheClienteDTO;
import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class LoginCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteDTO> dadosLoginCliente(String nome) {
        if (nome == null) {
            Iterable<Cliente> clientes = clienteRepository.findAll();

            return ClienteDTO.converter(clientes);
        } else {
            Iterable<Cliente> clientes = clienteRepository.findByNome(nome);

            return ClienteDTO.converter(clientes);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente clientesCadastro = form.converter();
        clienteRepository.save(clientesCadastro);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(clientesCadastro.getCodigoUsuario()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDTO(clientesCadastro));
    }

    @GetMapping("/{id}")
    public DetalheClienteDTO detalhe(@PathVariable String id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));

        return new DetalheClienteDTO(cliente);
    }
}