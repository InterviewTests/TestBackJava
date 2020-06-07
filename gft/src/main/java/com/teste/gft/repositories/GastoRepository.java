package com.teste.gft.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.gft.entities.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
	
	public List<Gasto> findAllByCodigoUsuario(Long codigoUsuario);
	public List<Gasto> findAllByCodigoUsuarioAndData(Long codigoUsuario, LocalDate data);
}
