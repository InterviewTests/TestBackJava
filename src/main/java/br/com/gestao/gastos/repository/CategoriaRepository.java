package br.com.gestao.gastos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gestao.gastos.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String>{
}
