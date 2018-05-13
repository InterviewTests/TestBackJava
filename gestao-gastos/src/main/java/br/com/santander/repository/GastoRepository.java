package br.com.santander.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.santander.model.GastoPorCartao;
/**
 * Repositório de Gasto
 * @author AntonioJolvino
 *
 */
public interface GastoRepository extends CrudRepository<GastoPorCartao, UUID>{
	
	@Query("SELECT * FROM gastos_por_cartao WHERE codigogasto = ?0")
	Optional<GastoPorCartao> findById(UUID id);

	@Query("SELECT * FROM gastos_por_cartao WHERE data >= ?0 and data <= ?1 and numerocartao = ?2 ALLOW FILTERING")
	List<GastoPorCartao> findByDataECartao(Date dataInicio, Date dataFim, Long numeroCartao);
	
	@Query("SELECT * FROM gastos_por_cartao WHERE numerocartao = ?0 ALLOW FILTERING")
	List<GastoPorCartao> findByNumeroCartao(Long numeroCartao);
}
