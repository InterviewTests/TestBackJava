package br.com.santander.gastos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.santander.gastos.model.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long> {

	List<Gasto> findByIdUsuarioOrderByDataDesc(Long id);

	@Query("SELECT g FROM Gasto g WHERE g.idUsuario = :id AND g.data >= :date order by g.data DESC")
	List<Gasto> findGastosByDate(@Param("id") Long id, @Param("date") LocalDateTime date);

	
}
