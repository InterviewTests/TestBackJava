package br.com.gestao.gastos.repository;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.gestao.gastos.model.Gastos;

public interface GastosRepository extends MongoRepository<Gastos, String> {

	@Query("{ 'codigousuario': ?0 }")
	List<Gastos> findAllByCodigoUsuario(int codigousuario, Sort sort);
	
	@Query("{ '_id': ?0, 'codigousuario': ?1 }")
	Gastos findByIdAndCodigoUsuario(ObjectId id, int codigousuario);
	
	@Query("{ 'codigousuario' : ?0, 'data': {$gte: ?1, $lte: ?2} }")
	List<Gastos> findAllByCodigoUsuarioData(int codigousuario, LocalDate dataInicio, LocalDate dataFim, Sort sort);

}
