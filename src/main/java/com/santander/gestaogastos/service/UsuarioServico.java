package com.santander.gestaogastos.service;

import java.util.List;

import com.santander.gestaogastos.exception.GastosException;

public interface UsuarioServico <T> {
	
	T salvarUsuario(T l) throws GastosException;
	
	public <E> List<T> listaUsuario();
	public <E> Object pesquisarUsuario(Integer id) throws GastosException;	
	public void removeUsuario(T entity) throws GastosException;

}
