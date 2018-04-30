package br.com.santander.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.api.model.Gasto;

@Repository
public interface GastosRepository extends CrudRepository<Gasto, Long>{

//	Gasto incluirGasto(Gasto gasto);
	
	List<Gasto> findByCodigoUsuario(Long codigoUsuario);

	List<Gasto> findByDataGasto(Long codigoUsuario, LocalDate dataGasto);
	
//	List<Gasto> pesquisarGastosUltimosCincoSegundosPorIdCliente(Long idCliente, LocalDateTime data);
	
}
