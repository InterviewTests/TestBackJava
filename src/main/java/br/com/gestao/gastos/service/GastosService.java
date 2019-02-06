package br.com.gestao.gastos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.gestao.gastos.model.Gastos;

public interface GastosService {

	List<Gastos> listAll();
	
	Optional<Gastos> getById(UUID id);
	
	Gastos save(Gastos gastos);
	
	void delete(UUID id);
	
}
