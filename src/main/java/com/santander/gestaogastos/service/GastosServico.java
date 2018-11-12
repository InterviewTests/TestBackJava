package com.santander.gestaogastos.service;

import java.util.List;

import com.santander.gestaogastos.exception.GastosException;

public interface GastosServico <T> {

	T salvarGasto(T l) throws GastosException; 
	
	public <E> List<T> listaGastos();
	public <E> Object pesquisarGasto(Integer id) throws GastosException;	
	public void removeGasto(T entity) throws GastosException;
}
	