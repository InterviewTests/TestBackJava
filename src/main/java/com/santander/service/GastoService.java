package com.santander.service;

import com.santander.model.Gasto;
import com.santander.repository.GastoRepositoy;

public class GastoService implements IGastoService {

	private GastoRepositoy gastosRepository;

	public GastoService(GastoRepositoy gastoRepository) {
		this.gastosRepository = gastoRepository;
	}

	@Override
	public Gasto salvar(Gasto gasto) {
		return gastosRepository.save(gasto);
	}

}
