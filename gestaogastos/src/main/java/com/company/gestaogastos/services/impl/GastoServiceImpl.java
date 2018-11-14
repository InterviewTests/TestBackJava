package com.company.gestaogastos.services.impl;

import java.util.List;

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

	public List<Gasto> retrieveAllGastos() {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		List<Gasto> gastos = gastoDomain.retrieveAllGastos();
		return gastos;
	}

	public Gasto retrieveGasto(long id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gasto = gastoDomain.retrieveGasto(id);
		return gasto;
	}

	public Page<Gasto> retrieveGastoByUser(Integer id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Page<Gasto> gastos = gastoDomain.retrieveGastoByUser(id);
		return gastos;
	}

	public Page<Gasto> retrieveGastoByUserDate(Integer id, String date) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Page<Gasto> gastos = gastoDomain.retrieveGastoByUserDate(id, date);
		return gastos;
	}

	public Gasto createGasto(Gasto gasto) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastoBase = gastoDomain.createGasto(gasto);
		return gastoBase;
	}
    
	public Gasto updateGasto(Gasto gasto, long id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastoBase = gastoDomain.updateGasto(gasto, id);
		return gastoBase;
	}

	public void deleteGasto(long id) {
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		gastoDomain.deleteGasto(id);
	}

}