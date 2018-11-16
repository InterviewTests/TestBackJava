package com.company.gestaogastos.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.gestaogastos.domain.GastoDomain;
import com.company.gestaogastos.domain.entity.Gasto;
import com.company.gestaogastos.domain.repository.CategoriaRepository;
import com.company.gestaogastos.domain.repository.GastoRepository;
import com.company.gestaogastos.services.GastoService;

@Service
public class GastoServiceImpl implements GastoService {

	@Autowired
	private GastoRepository gastoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Page<Gasto> retrieveGastos(Map<String, String> allRequestParams) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Page<Gasto> gastos = gastoDomain.retrieveGastos(allRequestParams);
		return gastos;
	}

	@Override
	public Gasto retrieveGasto(long id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gasto = gastoDomain.retrieveGasto(id);
		return gasto;
	}

//	@Override
//	public Page<Gasto> retrieveGastoByUser(Integer id) {
//		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
//		PageRequest pageRequest = new PageRequest(0, 10);
//		Page<Gasto> gastos = gastoDomain.retrieveGastoByUser(id, pageRequest);
//		return gastos;
//	}
//
//	@Override
//	public Page<Gasto> retrieveGastoByUserDate(Integer id, String date) {
//		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
//		PageRequest pageRequest = new PageRequest(0, 10);
//		Page<Gasto> gastos = gastoDomain.retrieveGastoByUserDate(id, date, pageRequest);
//		return gastos;
//	}

	@Override
	public Gasto createGasto(Gasto gasto) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastoBase = gastoDomain.createGasto(gasto);
		return gastoBase;
	}
    
	@Override
	public Gasto updateGasto(Gasto gasto, long id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastoBase = gastoDomain.updateGasto(gasto, id);
		return gastoBase;
	}

	@Override
	public void deleteGasto(long id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		gastoDomain.deleteGasto(id);
		gastoDomain.equals(null);
	}
	
	@Override
	public boolean equals(Object o) {
		return true;
	}

}