package com.company.gestaogastos.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.gestaogastos.domain.entity.Gasto;

//@Service
public interface GastoService {

	public List<Gasto> retrieveAllGastos();

	public Page<Gasto> retrieveGastos(Map<String, String> allRequestParams);

	public Gasto retrieveGasto(long id);

//	public Page<Gasto> retrieveGastoByUser(Integer id);
//
//	public Page<Gasto> retrieveGastoByUserDate(Integer id, String date);
	
	public Gasto createGasto(Gasto gasto);
	
	public Gasto updateGasto(Gasto gasto, long id);

	public void deleteGasto(long id);

}