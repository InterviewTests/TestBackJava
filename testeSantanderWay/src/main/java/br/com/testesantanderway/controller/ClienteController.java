package br.com.testesantanderway.controller;


import br.com.testesantanderway.controller.form.AtualizacaoClienteForm;
import br.com.testesantanderway.controller.form.ClienteForm;
import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.dto.DetalheClienteDTO;
import br.com.testesantanderway.modelo.Cliente;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @Cacheable(value = "listaDeCliente")
    public Page<ClienteDTO> dadosLoginCliente(@RequestParam(required = false) String nome,
                                              @PageableDefault(sort = "codigoUsuario",
                                              direction = Sort.Direction.ASC) Pageable paginacao) {

        if (nome == null || nome.isEmpty()) {
            Page<Cliente> clientes = clienteRepository.findAll(paginacao);

            return ClienteDTO.converter(clientes);
        } else {
            Page<Cliente> clientes = clienteRepository.findByNome(nome, paginacao);

            return ClienteDTO.converter(clientes);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        form.setSenha(new BCryptPasswordEncoder().encode(form.getSenha()));
        Cliente clientesCadastro = form.converter();
        clienteRepository.save(clientesCadastro);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(clientesCadastro.getCodigoUsuario()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDTO(clientesCadastro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheClienteDTO> detalhe(@PathVariable String id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(new DetalheClienteDTO(cliente.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeCliente", allEntries = true)
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable String id, @RequestBody @Valid AtualizacaoClienteForm form) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            Cliente cliente = form.atualizar(id, clienteRepository);
            return ResponseEntity.ok(new ClienteDTO(cliente));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeCliente", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable String id) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}