package com.company.gestaogastos.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.gestaogastos.domain.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	@Query("SELECT c FROM Categoria c")
	Page<Categoria> findAllCategoria(Pageable pageRequest);
	
	@Query("SELECT c FROM Categoria c WHERE INSTR(LOWER(c.nome), LOWER(:nome)) > 0 order by nome asc")
    public Page<Categoria> findByNome(
	    		@Param("nome") String nome,
	    		Pageable pageRequest);
}

