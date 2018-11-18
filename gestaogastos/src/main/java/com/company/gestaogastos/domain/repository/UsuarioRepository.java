package com.company.gestaogastos.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.gestaogastos.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u")
	Page<Usuario> findAllUsuario(Pageable pageRequest);
	
	@Query("SELECT u FROM Usuario u WHERE INSTR(LOWER(u.nome), LOWER(:nome)) > 0 order by nome asc")
    public Page<Usuario> findByNome(
	    		@Param("nome") String nome,
	    		Pageable pageRequest);
}

