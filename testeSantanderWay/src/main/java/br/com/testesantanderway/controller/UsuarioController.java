package br.com.testesantanderway.controller;

import br.com.testesantanderway.controller.form.AtualizacaoUsuarioForm;
import br.com.testesantanderway.controller.form.UsuarioForm;
import br.com.testesantanderway.dto.DetalheUsuarioDTO;
import br.com.testesantanderway.dto.UsuarioDTO;
import br.com.testesantanderway.modelo.Usuario;
import br.com.testesantanderway.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

//    @GetMapping
//    @Cacheable(value = "listaDeUsuario")
//    public Page<UsuarioDTO> dadosLoginUsuario(@RequestParam(required = false) String nomeUsuario,
//                                              @PageableDefault(sort = "codigoUsuario",
//                                                      direction = Sort.Direction.ASC) Pageable paginacao) {
//
//        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
//            Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
//
//            return UsuarioDTO.converter(usuarios);
//        } else {
//            Page<Usuario> usuarios = usuarioRepository.findByNomeUsuario(nomeUsuario, paginacao);
//
//            return UsuarioDTO.converter(usuarios);
//        }
//    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        form.setSenha(new BCryptPasswordEncoder().encode(form.getSenha()));
        Usuario usuarioCadastro = form.converter();
        usuarioRepository.save(usuarioCadastro);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(usuarioCadastro.getCodigoUsuario()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDTO(usuarioCadastro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheUsuarioDTO> detalhe(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(new DetalheUsuarioDTO(usuario.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeUsuario", allEntries = true)
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable String id, @RequestBody @Valid AtualizacaoUsuarioForm form) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = form.atualizar(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDTO(usuario));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeUsuario", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable String id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
