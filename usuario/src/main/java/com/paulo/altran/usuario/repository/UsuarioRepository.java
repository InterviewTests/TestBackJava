package com.paulo.altran.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulo.altran.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByAcesso(String acesso);

	boolean findExistById(Long id);

}
