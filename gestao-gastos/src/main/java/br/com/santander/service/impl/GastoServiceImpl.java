package br.com.santander.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.builder.GastoPorCartaoModelToVOBuilder;
import br.com.santander.builder.GastoVoToModelBuilder;
import br.com.santander.model.GastoPorCartao;
import br.com.santander.repository.GastoRepository;
import br.com.santander.service.GastoService;
import br.com.santander.utils.DateUtils;
import br.com.santander.vo.GastoVO;

/**Classe de implementação do serviço de Gasto
 * 
 * @author AntonioJolvino
 *
 */
@Service
public class GastoServiceImpl implements GastoService {
	
	@Autowired
	private GastoRepository gastosRepository;
	
	public GastoVO findById(UUID codigoGasto) {
		GastoPorCartao gastoModel = gastosRepository.findById(codigoGasto).orElse(null);
		if(gastoModel != null) {
			return new GastoPorCartaoModelToVOBuilder(gastoModel).build();
		} else {
			return null;
		} 
	}

	@Override
	public void deleteAll() {
		gastosRepository.deleteAll();
	}
	
	@Override
	public void save(Long numeroCarto, GastoVO gastoVO) {
		
		GastoPorCartao gastoModel = new GastoVoToModelBuilder(numeroCarto, gastoVO).build();
		
		gastosRepository.save(gastoModel);
	}
	
	@Override
	public GastoVO atualizaCategoria(UUID codigoGasto, String categoria) {
		GastoPorCartao gastoModel = gastosRepository.findById(codigoGasto).orElse(null);
		if(gastoModel == null) {
			return null;
		}
		
		gastoModel.setCategoria(categoria);
		
		GastoPorCartao gastoSalvo = gastosRepository.save(gastoModel);
		
		return new GastoPorCartaoModelToVOBuilder(gastoSalvo).build();
	}

	@Override
	public void delete(GastoPorCartao gasto) {
		gastosRepository.delete(gasto);
	}

	@Override
	public void deleteById(UUID id) {
		gastosRepository.deleteById(id);
	}

	@Override
	public List<GastoVO> findByDataECartao(String dateAsString, Long numeroCartao) {
		Date data = DateUtils.stringToDate(dateAsString);
		List<GastoPorCartao> gastosModel = gastosRepository.findByDataECartao(DateUtils.getDataInicio(data), DateUtils.getDataFim(data), numeroCartao);
		List<GastoVO> gastosVO = new GastoPorCartaoModelToVOBuilder(gastosModel).buildList();
		
		return gastosVO;
	}

	@Override
	public List<GastoVO> findByNumeroCartao(Long numeroCartao) {
		List<GastoPorCartao> gastosModel = gastosRepository.findByNumeroCartao(numeroCartao);
		
		List<GastoVO> gastosVO = new GastoPorCartaoModelToVOBuilder(gastosModel).buildList();
		
		if(!gastosVO.isEmpty()) {
			return gastosVO;
		}
		return null;
	}

	@Override
	public List<GastoVO> findAll() {
		List<GastoPorCartao> gastosModel = ((List<GastoPorCartao>) gastosRepository.findAll());
		
		List<GastoVO> gastosVO = new ArrayList<GastoVO>();

		if(gastosModel != null && !gastosModel.isEmpty()) {
			gastosVO = new GastoPorCartaoModelToVOBuilder(gastosModel).buildList();
		}
		
		return gastosVO;
	}
}
