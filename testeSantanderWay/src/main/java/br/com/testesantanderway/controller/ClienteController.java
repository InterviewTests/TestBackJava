package br.com.testesantanderway.controller;

import br.com.testesantanderway.controller.form.AtualizacaoClienteForm;
import br.com.testesantanderway.controller.form.ClienteForm;
import br.com.testesantanderway.dto.ClienteDTO;
import br.com.testesantanderway.dto.DetalheClienteDTO;
import br.com.testesantanderway.modelo.Sistema;
import br.com.testesantanderway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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
    public Page<ClienteDTO> dadosLoginCliente(@RequestParam(required = false) String nomeCliente,
                                              @PageableDefault(sort = "codigoCliente",
                                              direction = Sort.Direction.ASC) Pageable paginacao) {

        if (nomeCliente == null || nomeCliente.isEmpty()) {
            Page<Sistema> clientes = clienteRepository.findAll(paginacao);

            return ClienteDTO.converter(clientes);
        } else {
            Page<Sistema> clientes = clienteRepository.findByNomeCliente(nomeCliente, paginacao);

            return ClienteDTO.converter(clientes);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        form.setSenha(new BCryptPasswordEncoder().encode(form.getSenha()));
        Sistema clientesCadastro = form.converter();
        clienteRepository.save(clientesCadastro);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(clientesCadastro.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDTO(clientesCadastro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheClienteDTO> detalhe(@PathVariable String id) {
        Optional<Sistema> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(new DetalheClienteDTO(cliente.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeCliente", allEntries = true)
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable String id, @RequestBody @Valid AtualizacaoClienteForm form) {
        Optional<Sistema> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            Sistema sistema = form.atualizar(id, clienteRepository);
            return ResponseEntity.ok(new ClienteDTO(sistema));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeCliente", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable String id) {
        Optional<Sistema> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}