package com.santander.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.model.Gasto;
import com.santander.repository.GastoRepositoy;

@Service
public class GastoService implements IGastoService {

	@Autowired
	private GastoRepositoy gastosRepository;

	public GastoService(GastoRepositoy gastoRepository) {
		this.gastosRepository = gastoRepository;
	}

	@Override
	public Gasto salvar(Gasto gasto) {
		return gastosRepository.save(gasto);
	}

}
