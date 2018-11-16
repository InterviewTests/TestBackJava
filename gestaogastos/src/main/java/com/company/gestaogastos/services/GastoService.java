package com.company.gestaogastos.services;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.gestaogastos.domain.entity.Gasto;

public interface GastoService {

	public Page<Gasto> retrieveGastos(Map<String, String> allRequestParams);

	public Gasto retrieveGasto(long id);

	public Gasto createGasto(Gasto gasto);
	
	public Gasto updateGasto(Gasto gasto, long id);

	public void deleteGasto(long id);

}