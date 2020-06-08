package com.paulo.altran.categoria.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.paulo.altran.categoria.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Optional<Categoria> findByNome(String nome);

	Page<Categoria> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
