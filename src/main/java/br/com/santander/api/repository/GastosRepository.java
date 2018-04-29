package br.com.santander.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.api.model.Gasto;

@Repository
public interface GastosRepository extends JpaRepository<Gasto, Long>{

	List<Gasto> findByCodigoUsuario(Long codigoUsuario);

//	List<Gasto> findByData(Long idCliente, LocalDateTime dataGasto);
	
//	List<Gasto> pesquisarGastosUltimosCincoSegundosPorIdCliente(Long idCliente, LocalDateTime data);
	
}
