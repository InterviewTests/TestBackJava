package br.com.brunots.testes.everis.service;

import java.util.Date;
import java.util.List;

import br.com.brunots.testes.everis.entity.CategoriaEntity;
import br.com.brunots.testes.everis.entity.GastoEntity;

public interface GastosService {

	void save(GastoEntity entity);

	List<GastoEntity> listAll();

	List<GastoEntity> listAllByName(String name);

	List<GastoEntity> listAllByNameWithDate(String name, Date parse);

	void incluirCategoria(String gastoId, CategoriaEntity categoria);

}
