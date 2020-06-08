package com.paulo.altran.gasto.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paulo.altran.gasto.model.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long> {

	Optional<Gasto> findFirst1BycodigoUsuarioAndDescricaoIgnoreCaseAndCodigoCategoriaNotNull(Long codigoUsuario,
			String descricao);
	
	Optional<Gasto> findByIdAndCodigoUsuario(Long id, Long usuarioId);
	
	@Query("SELECT gasto from Gasto gasto where "
			+ "( (year(:data) is null or year(gasto.data) = year(:data)) "
				+ "AND (month(:data) is null or month(gasto.data) = month(:data)) AND (day(:data) is null or day(gasto.data) = day(:data))) AND gasto.codigoUsuario = :idUsuario")
	Page<Gasto> buscarData(LocalDate data, Long idUsuario, Pageable pageable);

}
