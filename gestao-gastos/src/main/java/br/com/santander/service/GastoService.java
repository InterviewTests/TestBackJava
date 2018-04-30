package br.com.santander.service;

import java.util.List;
import java.util.UUID;

import br.com.santander.model.Gasto;
import br.com.santander.vo.CartaoVO;
import br.com.santander.vo.GastoVO;

public interface GastoService {
	List<CartaoVO> findAll();
	
	void deleteAll();
	
	CartaoVO save(CartaoVO cartaoVO);
	
	void save(Long numeroCarto, GastoVO gastoVO);
	
	void delete(Gasto gasto);
	
	void deleteById(UUID id);
	
	List<CartaoVO> findByData(String dateAsString);
	
	CartaoVO findByNumeroCartao(Long numeroCartao);

}
