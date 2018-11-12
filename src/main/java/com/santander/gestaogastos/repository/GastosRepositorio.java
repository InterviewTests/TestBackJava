package com.santander.gestaogastos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santander.gestaogastos.domain.Gasto;

public interface GastosRepositorio extends JpaRepository<Gasto, Integer> {
	
	@Query("SELECT g.categoria.descricao FROM Gasto g WHERE g.usuario.id = :id")
    public List<String> buscarCategoriasDoCliente(@Param("id") Integer id);
	
	@Query("SELECT p FROM Gasto p WHERE p.data = :data order by p.data desc")
	public List<Gasto> findByDate(@Param("data") Date data );

}
