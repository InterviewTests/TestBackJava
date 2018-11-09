package com.santander.gestaogastos.service;

import java.util.List;

public interface GastosServico <T> {

	T salvarGasto(T l); 
	
	public <E> List<T> listaGastos();
	public <E> Object pesquisarGasto(Integer id);	
	public void removeGasto(T entity);
}
	