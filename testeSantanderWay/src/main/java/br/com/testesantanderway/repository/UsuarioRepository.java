package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;


public interface UsuarioRepository extends SolrCrudRepository<Usuario, String> {
    Page<Usuario> findByNomeUsuario(String nomeUsuario, Pageable paginacao);
    Optional<Usuario> findByEmail(String email);
}
