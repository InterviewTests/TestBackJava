package com.santander.gestaogastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.gestaogastos.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	
}
