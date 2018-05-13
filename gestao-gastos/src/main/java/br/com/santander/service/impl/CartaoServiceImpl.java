package br.com.santander.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.builder.CartaoModelToVOBuilder;
import br.com.santander.builder.CartaoVoToModelBuilder;
import br.com.santander.model.Cartao;
import br.com.santander.repository.CartaoRepository;
import br.com.santander.service.CartaoService;
import br.com.santander.vo.CartaoVO;

/**
 * Classe de implementação do serviço de Cartao
 * @author AntonioJolvino
 *
 */
@Service
public class CartaoServiceImpl implements CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@Override
	public List<CartaoVO> findAll() {
		List<Cartao> cartoes = (List<Cartao>) cartaoRepository.findAll();
		
		List<CartaoVO> cartoesVO = new  ArrayList<CartaoVO>();
		if(cartoes != null && !cartoes.isEmpty()) {
			cartoesVO.addAll(new CartaoModelToVOBuilder(cartoes).buildList());
		}
		return cartoesVO;
	}
	
	@Override
	public CartaoVO findById(Long numeroCartao) {
		Cartao cartaoModel = cartaoRepository.findById(numeroCartao).orElse(null);
		
		CartaoVO cartaoVO = null;
		
		if(cartaoModel != null) {
			cartaoVO = new CartaoModelToVOBuilder(cartaoModel).build();
		}
		
		return cartaoVO;
	}
	
	@Override
	public Cartao save(CartaoVO cartao) {
		return cartaoRepository.save(new CartaoVoToModelBuilder(cartao).build());
	}

	@Override
	public void deleteAll() {
		cartaoRepository.deleteAll();
		
	}

	@Override
	public void deleteById(Long id) {
		cartaoRepository.deleteById(id);		
	}

	@Override
	public List<CartaoVO> findByCodigoUsuario(Long codigoUsuario) {
		cartaoRepository.findByCodigoUsuario(codigoUsuario);
		return null;
	}
}
