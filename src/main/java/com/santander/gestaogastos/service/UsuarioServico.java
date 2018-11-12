package com.santander.gestaogastos.service;

import java.util.List;

public interface UsuarioServico <T> {
	
	T salvarUsuario(T l);
	
	public <E> List<T> listaUsuario();
	public <E> Object pesquisarUsuario(Integer id);	
	public void removeUsuario(T entity);

}
