package com.santander.service;

import java.util.List;

import com.santander.model.Gasto;
import com.santander.repository.GastoRepositoy;

public class GastoService implements IGastoService {

	private GastoRepositoy gastosRepository;

	public GastoService(GastoRepositoy gastoRepository) {
		this.gastosRepository = gastoRepository;
	}

	@Override
	public void salvar(List<Gasto> listGasto) {

		for (Gasto gasto : listGasto) {
			gastosRepository.save(gasto);
		}

	}

}
