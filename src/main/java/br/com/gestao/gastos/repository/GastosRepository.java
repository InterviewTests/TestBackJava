package br.com.gestao.gastos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.gestao.gastos.model.Gastos;

public interface GastosRepository extends MongoRepository<Gastos, String> {

	@Query("{ 'codigousuario' : ?0 }")
	List<Gastos> findAllByCodigoUsuario(int codigousuario);
	
}
