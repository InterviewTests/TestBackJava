package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Usuario;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;


public interface UsuarioRepository extends SolrCrudRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
