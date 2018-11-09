package com.santander.gestaogastos.service;

import java.util.List;

public interface CategoriaServico <T> {
	
	T salvarCategoria(T l);
	
	public <E> List<T> listaCategoria();
	public <E> Object pesquisarCategoria(Integer id);	
	public void removeCategoria(T entity);

}
