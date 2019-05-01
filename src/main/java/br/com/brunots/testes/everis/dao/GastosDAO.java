package br.com.brunots.testes.everis.dao;

import java.util.List;

import br.com.brunots.testes.everis.entity.GastoEntity;

public interface GastosDAO {

	void save(GastoEntity entity);

	List<GastoEntity> listAll();

	List<GastoEntity> listAllByCodigousuario(Integer codigousuario);

}
