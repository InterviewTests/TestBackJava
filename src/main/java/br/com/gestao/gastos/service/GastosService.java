package br.com.gestao.gastos.service;

import java.util.List;

import br.com.gestao.gastos.model.Gastos;

public interface GastosService {

	List<Gastos> listAll();
	
	Gastos findByCodigoUsuario(String codigousuario);
	
	Gastos save(Gastos gastos);
	
}
