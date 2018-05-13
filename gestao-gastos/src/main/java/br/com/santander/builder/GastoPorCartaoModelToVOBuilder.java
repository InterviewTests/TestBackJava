package br.com.santander.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.model.GastoPorCartao;
import br.com.santander.vo.GastoVO;
/**
 * Builder responsável por construir um objeto de VO do Gasto a partir do modelo
 * @author AntonioJolvino
 *
 */
public class GastoPorCartaoModelToVOBuilder {

	private List<GastoPorCartao> gastoModelList;

	private GastoPorCartao gastoModel;
	
	public GastoPorCartaoModelToVOBuilder(GastoPorCartao gastoModel) {
		this.gastoModel = gastoModel;
	}
	
	public GastoPorCartaoModelToVOBuilder(List<GastoPorCartao> gastoModelList) {
		this.gastoModelList = gastoModelList;
	}

	public List<GastoVO> buildList() {
		List<GastoVO> gastosVO = new ArrayList<GastoVO>();
		for (GastoPorCartao gastoItem : gastoModelList) {
			GastoVO gastoVO = new GastoVO(gastoItem.getCodigoGasto(), gastoItem.getNumeroCartao(),
					gastoItem.getCategoria(), gastoItem.getDescricao(), gastoItem.getValor(),
					gastoItem.getCodigoUsuario(), gastoItem.getData());
			gastosVO.add(gastoVO);
		}

		return gastosVO;
	}
	
	public GastoVO build() {
			GastoVO gastoVO = new GastoVO(gastoModel.getCodigoGasto(), gastoModel.getNumeroCartao(),
					gastoModel.getCategoria(), gastoModel.getDescricao(), gastoModel.getValor(),
					gastoModel.getCodigoUsuario(), gastoModel.getData());

		return gastoVO;
	}
}
