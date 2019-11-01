package com.gabrieldemery.gestaogastos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrieldemery.gestaogastos.entities.Gasto;

@Repository
public interface GastosRepository extends JpaRepository<Gasto, Long> {

	@Query("SELECT G FROM Gasto G WHERE DATE(G.data) = STR_TO_DATE(:data, '%d/%m/%Y')")
	List<Gasto> findByData(String data);
	
	Gasto findByCodigo(Long codigo);
	
	Optional<Gasto> findFirstByDescricaoAndCategoriaIsNotNull(String descricao);
	
}
