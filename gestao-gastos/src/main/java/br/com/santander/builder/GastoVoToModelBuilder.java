package br.com.santander.builder;

import java.util.UUID;

import br.com.santander.model.Gasto;
import br.com.santander.vo.GastoVO;

public class GastoVoToModelBuilder {
	
	private Long numeroCartao;
	
	private GastoVO gastoVO;
	
	public GastoVoToModelBuilder (Long numeroCartao, GastoVO gastoVO) {
		this.numeroCartao = numeroCartao;
		this.gastoVO = gastoVO;
	}
	
	public Gasto build() {
		UUID id = gastoVO.getCodigoGasto() != null ? gastoVO.getCodigoGasto() : UUID.randomUUID();
		
		Gasto gastoRetorno = new Gasto(id, numeroCartao, gastoVO.getDescricao(), gastoVO.getValor(), gastoVO.getCodigoUsuario(), gastoVO.getDataAsDate());
		
		return gastoRetorno;
	}
	
}
