package br.com.santander.builder;

import br.com.santander.model.Cartao;
import br.com.santander.vo.CartaoVO;

/**
 * Builder responsável por construir um objeto de Modelo do Cartao a partir do VO
 * @author AntonioJolvino
 *
 */
public class CartaoVoToModelBuilder {
	
	
	private CartaoVO cartaoVO;
	
	public CartaoVoToModelBuilder (CartaoVO cartaoVO) {
		this.cartaoVO = cartaoVO;
	}
	
	public Cartao build() {
		
		Cartao cartaoModel = new Cartao(cartaoVO.getNumeroCartao(), cartaoVO.getCodigoUsuario(), cartaoVO.getDataContrato());
		
		return cartaoModel;
	}
	
}
