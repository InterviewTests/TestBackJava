package br.com.santander.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.model.Cartao;
import br.com.santander.vo.CartaoVO;

/**
 * Builder responsável por construir um objeto de VO do Cartao a partir do modelo
 * @author AntonioJolvino
 *
 */
public class CartaoModelToVOBuilder {

	private Cartao cartaoModel;

	private List<Cartao> cartaoModelList;

	public CartaoModelToVOBuilder(Cartao cartaoModel) {
		this.cartaoModel = cartaoModel;
	}

	public CartaoModelToVOBuilder(List<Cartao> cartaoModelList) {
		this.cartaoModelList = cartaoModelList;
	}

	public CartaoVO build() {
		CartaoVO cartaoVO = new CartaoVO(cartaoModel.getNumeroCartao(), cartaoModel.getCodigoUsuario(),
				cartaoModel.getDataContrato());
		return cartaoVO;
	}

	public List<CartaoVO> buildList() {
		List<CartaoVO> cartaoVOList = new ArrayList<CartaoVO>();

		for (Cartao cartao : cartaoModelList) {
			cartaoVOList
					.add(new CartaoVO(cartao.getNumeroCartao(), cartao.getCodigoUsuario(), cartao.getDataContrato()));
		}

		return cartaoVOList;
	}
}
