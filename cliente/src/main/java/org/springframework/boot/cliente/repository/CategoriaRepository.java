package org.springframework.boot.cliente.repository;

import org.springframework.boot.cliente.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
}
