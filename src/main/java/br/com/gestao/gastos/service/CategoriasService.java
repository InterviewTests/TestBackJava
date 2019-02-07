package br.com.gestao.gastos.service;

import java.util.List;

import br.com.gestao.gastos.model.Categorias;

public interface CategoriasService {

	List<Categorias> listaCategoriasSugeridas();
	
	Categorias findCategeoria(String categoria);

	Categorias save(Categorias categoria);

}
