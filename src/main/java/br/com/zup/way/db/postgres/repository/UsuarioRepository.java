package br.com.zup.way.db.postgres.repository;

import br.com.zup.way.db.postgres.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findByIdAndTipo(Long codUsuario, Integer tipo);
}
