package br.com.gestao.gastos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.gestao.gastos.model.Categorias;

public interface CategoriasRepository extends MongoRepository<Categorias, String> {

	@Query("{ 'categoria': ?0 }")
	Categorias findByNomeCategoria(String categoria);

	@Query("{ 'categoria': {$regex: ?0, $options: 'i'} }")
	List<Categorias> findCategoriasSugeridas(String categoria);

}
