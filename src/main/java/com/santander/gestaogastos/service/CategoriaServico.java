package com.santander.gestaogastos.service;

import java.util.List;

import com.santander.gestaogastos.exception.GastosException;

public interface CategoriaServico <T> {
	
	T salvarCategoria(T l);
	
	public <E> List<T> listaCategoria();
	public <E> Object pesquisarCategoria(Integer id) throws GastosException;	
	public void removeCategoria(T entity) throws GastosException;

}
