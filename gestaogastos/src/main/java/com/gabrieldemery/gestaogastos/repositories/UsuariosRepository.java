package com.gabrieldemery.gestaogastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrieldemery.gestaogastos.entities.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsuario(String usuario);
	
}
