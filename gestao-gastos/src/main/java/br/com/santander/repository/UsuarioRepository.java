package br.com.santander.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.santander.model.Usuario;

/**
 * Repositório de usuários
 * @author AntonioJolvino
 *
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	@Query("SELECT * FROM usuarios WHERE codigousuario = ?0")
	Optional<Usuario> findById(Long id);
}
