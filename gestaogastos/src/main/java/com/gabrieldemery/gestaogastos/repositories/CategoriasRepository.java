package com.gabrieldemery.gestaogastos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrieldemery.gestaogastos.entities.Categoria;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Long> {
	
	Optional<List<Categoria>> findByNome(String nome);
	
	List<Categoria> findByNomeContains(String nome);
}
