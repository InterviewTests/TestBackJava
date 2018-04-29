package br.com.santander.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.api.model.Gasto;

@Repository
public interface GastosRepository extends JpaRepository<Gasto, Long>{

	List<Gasto> pesquisarGastosUltimosCincoSegundosPorIdCliente(Long idCliente, LocalDateTime data);
	
	List<Gasto> pesquisarGastosPorIdCliente(Long idCliente);

	List<Gasto> pesquisarGastosIntervaloDatasPorIdCliente(Long idCliente, LocalDateTime dataInicio, LocalDateTime dataFim);
	
}
