package br.com.santander.builder;

import java.util.UUID;

import br.com.santander.model.GastoPorCartao;
import br.com.santander.vo.GastoVO;
/**
 * Builder responsável por construir um objeto de modelo do Gasto a partir do VO
 * @author AntonioJolvino
 *
 */
public class GastoVoToModelBuilder {
	
	private Long numeroCartao;
	
	private GastoVO gastoVO;
	
	public GastoVoToModelBuilder (Long numeroCartao, GastoVO gastoVO) {
		this.numeroCartao = numeroCartao;
		this.gastoVO = gastoVO;
	}
	
	public GastoPorCartao build() {
		UUID id;
		if(gastoVO.getCodigoGasto() == null) {
			String sNumeroCartao = numeroCartao != null ? numeroCartao.toString() : "";
			String descricao = gastoVO.getDescricao() != null ? gastoVO.getDescricao() : "";
			String chave = sNumeroCartao + descricao;
			id = UUID.nameUUIDFromBytes(chave.getBytes());
		} else {
			id = gastoVO.getCodigoGasto();
		}
		
		GastoPorCartao gastoRetorno = new GastoPorCartao(id, numeroCartao, gastoVO.getCategoria(),gastoVO.getDescricao(), gastoVO.getValor(), gastoVO.getCodigoUsuario(), gastoVO.getData());
		
		return gastoRetorno;
	}
	
}
