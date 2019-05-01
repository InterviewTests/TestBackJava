package br.com.brunots.testes.everis.service;

import java.util.List;

import br.com.brunots.testes.everis.entity.GastoEntity;

public interface GastosService {

	void save(GastoEntity entity);

	List<GastoEntity> listAll();

}
