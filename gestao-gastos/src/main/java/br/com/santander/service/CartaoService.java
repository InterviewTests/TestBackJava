package br.com.santander.service;

import java.util.List;

import br.com.santander.model.Cartao;
import br.com.santander.vo.CartaoVO;

/**
 * Interface do serviço de cartão
 * @author AntonioJolvino
 *
 */
public interface CartaoService {
	
	List<CartaoVO> findAll();
	
	CartaoVO findById(Long numeroCartao);
	
	
	Cartao save(CartaoVO cartao);
	
	void deleteAll();
	
	void deleteById(Long id);
	
	List<CartaoVO> findByCodigoUsuario(Long codigoUsuario);

}
