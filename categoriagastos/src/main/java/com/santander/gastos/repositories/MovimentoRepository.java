package com.santander.gastos.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.santander.gastos.domain.Cliente;
import com.santander.gastos.domain.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT mv FROM Movimento mv " + 
		   " INNER JOIN mv.cartao car " +
		   " INNER JOIN car.conta cta " +
		   " INNER JOIN cta.cliente cli" +
		   " WHERE cli.codigoUsuario = :codigoCliente")
	Page<Movimento> findMovimentoByCodCli(
											@Param("codigoCliente") Long codigoCliente, 
											Pageable pageRequest );

	@Transactional(readOnly=true)
	@Query("SELECT mv FROM Movimento mv " + 
		   " INNER JOIN mv.cartao car " +
		   " INNER JOIN car.conta cta " +
		   " INNER JOIN cta.cliente cli" +
		   " WHERE cli.codigoUsuario = :codigoCliente" +
		   " AND  DAY(mv.data) = DAY(:dataFiltro) AND MONTH(mv.data) = MONTH(:dataFiltro) AND YEAR(mv.data) = YEAR( :dataFiltro)  ")
	Page<Movimento> findMovimentoByCodCliAndDate(
												@Param("codigoCliente") Long codigoCliente, 
												@Param("dataFiltro")  Date dataFiltro, 
												Pageable pageRequest );

	
	
	
	@Transactional(readOnly=true)
	@Query("SELECT mv FROM Movimento mv " + 
		   " INNER JOIN mv.cartao car " +
		   " INNER JOIN car.conta cta " +
		   " INNER JOIN cta.cliente cli" +
		   " WHERE cli.codigoUsuario = :codigoCliente")
	Movimento findMovimentoByCodCli(
											@Param("codigoCliente") Long codigoCliente);
	@Transactional(readOnly=true)
	Optional<Movimento> findById(Integer id);
	
}
