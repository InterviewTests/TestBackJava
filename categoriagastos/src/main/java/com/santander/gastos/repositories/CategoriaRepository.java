package com.santander.gastos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.santander.gastos.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Transactional(readOnly=true)
	@Query("FROM Categoria where nome LIKE %:categoria%")
	Optional<List<Categoria>> buscarPorCatnome(@Param("categoria") String categoria);

}
