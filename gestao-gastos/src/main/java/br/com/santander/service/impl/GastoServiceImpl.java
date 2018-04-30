package br.com.santander.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.builder.CartaoModelToVOBuilder;
import br.com.santander.builder.GastoVoToModelBuilder;
import br.com.santander.model.Gasto;
import br.com.santander.repository.GastosRepository;
import br.com.santander.service.GastoService;
import br.com.santander.utils.DateUtils;
import br.com.santander.vo.CartaoVO;
import br.com.santander.vo.GastoVO;

@Service
public class GastoServiceImpl implements GastoService {
	
	@Autowired
	private GastosRepository gastosRepository;

	@Override
	public List<CartaoVO> findAll() {
		List<Gasto> gastosModel = ((List<Gasto>) gastosRepository.findAll());
		
		if(gastosModel != null) {
			return new CartaoModelToVOBuilder(gastosModel).build();
		}
		
		return new ArrayList<CartaoVO>(); 
	}

	@Override
	public void deleteAll() {
		gastosRepository.deleteAll();
		
	}

	@Override
	public CartaoVO save(CartaoVO cartaoVO) {
		List<Gasto> gastosSalvos = new ArrayList<Gasto>();
		
		for (GastoVO gastoVO : cartaoVO.getGastos()) {
			Gasto gastoModel = new GastoVoToModelBuilder(cartaoVO.getNumeroCartao(), gastoVO).build();
			Gasto gastoSalvo = gastosRepository.save(gastoModel);
			if(gastoSalvo != null) {
				gastosSalvos.add(gastoSalvo);
			}
		}
		
		List<CartaoVO> cartoesSalvos = new CartaoModelToVOBuilder(gastosSalvos).build();
		
		if(!cartoesSalvos.isEmpty()) {
			cartoesSalvos.get(0);
		}
		
		return null;
	}
	
	@Override
	public void save(Long numeroCarto, GastoVO gastoVO) {
		
		Gasto gastoModel = new GastoVoToModelBuilder(numeroCarto, gastoVO).build();
		
		gastosRepository.save(gastoModel);
	}

	@Override
	public void delete(Gasto gasto) {
		gastosRepository.delete(gasto);
	}

	@Override
	public void deleteById(UUID id) {
		gastosRepository.deleteById(id);
	}

	@Override
	public List<CartaoVO> findByData(String dateAsString) {
		Date data = DateUtils.stringToDate(dateAsString, DateUtils.FORMATO_UTC_BASE);
		List<Gasto> gastos = gastosRepository.findByData(DateUtils.getDataInicio(data), DateUtils.getDataFim(data));
		List<CartaoVO> cartoes = new CartaoModelToVOBuilder(gastos).build();
		
		return cartoes;
	}

	@Override
	public CartaoVO findByNumeroCartao(Long numeroCartao) {
		List<Gasto> gastos = gastosRepository.findByNumeroCartao(numeroCartao);
		
		List<CartaoVO> cartoes = new CartaoModelToVOBuilder(gastos).build();
		
		if(!cartoes.isEmpty()) {
			return cartoes.get(0);
		}
		
		return null;
	}
	
}
