package br.com.santander.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.santander.gastos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByDescricao(String descricao);
}
