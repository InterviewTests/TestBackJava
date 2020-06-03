package br.com.gft.gastos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.gastos.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>{
	List<Gasto> findByCliente(Long id);
	List<Gasto> findByClienteAndData(Long id, Date data);
	
}
