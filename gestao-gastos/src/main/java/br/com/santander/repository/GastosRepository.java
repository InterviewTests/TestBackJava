package br.com.santander.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.santander.model.Gasto;

public interface GastosRepository extends CrudRepository<Gasto, UUID>{
	
	@Query("SELECT * FROM gastos WHERE codigogasto = ?0")
	Optional<Gasto> findById(UUID id);

	@Query("SELECT * FROM gastos WHERE data >= ?0 and data <= ?1 ALLOW FILTERING")
	List<Gasto> findByData(Date dataInicio, Date dataFim);
	
	@Query("SELECT * FROM gastos WHERE numerocartao = ?0 ALLOW FILTERING")
	List<Gasto> findByNumeroCartao(Long numeroCartao);
}
