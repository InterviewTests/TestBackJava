package br.com.brunots.testes.everis.dao;

import java.util.Date;
import java.util.List;

import br.com.brunots.testes.everis.entity.CategoriaEntity;
import br.com.brunots.testes.everis.entity.GastoEntity;

public interface GastosDAO {

	void save(GastoEntity entity);

	List<GastoEntity> listAll();

	List<GastoEntity> listAllByCodigousuario(Integer codigousuario);

	List<GastoEntity> listAllByCodigousuarioWithDate(Integer codigousuario, Date date);

	GastoEntity getById(Long valueOf);

	List<CategoriaEntity> listarCategorias(String startsWith);

}
