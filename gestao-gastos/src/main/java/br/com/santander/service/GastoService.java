package br.com.santander.service;

import java.util.List;
import java.util.UUID;

import br.com.santander.model.GastoPorCartao;
import br.com.santander.vo.GastoVO;

/**
 * Interface do serviço de gasto
 * @author AntonioJolvino
 *
 */
public interface GastoService {

	List<GastoVO> findAll();

	GastoVO findById(UUID codigoGasto);

	void save(Long numeroCarto, GastoVO gastoVO);

	GastoVO atualizaCategoria(UUID codigoGasto, String categoria);

	void deleteAll();

	void delete(GastoPorCartao gasto);

	void deleteById(UUID id);

	List<GastoVO> findByDataECartao(String dateAsString, Long numeroCartao);

	List<GastoVO> findByNumeroCartao(Long numeroCartao);

}
