/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.function;

import java.util.function.Function;

import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.model.entity.GastoCartao;

/**
 * Functions para o objeto Gasto Cartão.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:47:56
 * @version x.x
 */
public final class GastoCartaoFunction {

	/**
	 * Atributo CONVERTER_GASTO_CARTAO_TO_DTO
	 */
	public final static Function<GastoCartao, GastoCartaoDTO> CONVERTER_GASTO_CARTAO_TO_DTO = new Function<GastoCartao, GastoCartaoDTO>() {

		@Override
		public GastoCartaoDTO apply(GastoCartao entrada) {
			GastoCartaoDTO saida = new GastoCartaoDTO();
			if (entrada != null) {
				saida.setDescricao(entrada.getDescricao());
				saida.setId(entrada.getId());
				saida.setValor(entrada.getValor());
				saida.setData(entrada.getData());
				saida.setCodigoUsuario(entrada.getCodigoUsuario());
				saida.setCategoria(CategoriaFunction.CONVERTER_ID_CATEGORIA_TO_DTO.apply(entrada.getIdCategoria()));
			}
			return saida;
		}

	};
	
	/**
	 * Atributo CONVERTER_GASTO_CARTAO_TO_ENTITY
	 */
	public final static Function<GastoCartaoDTO, GastoCartao> CONVERTER_GASTO_CARTAO_TO_ENTITY = new Function<GastoCartaoDTO, GastoCartao>() {

		@Override
		public GastoCartao apply(GastoCartaoDTO entrada) {
			GastoCartao saida = new GastoCartao();
			if (entrada != null) {
				if (entrada.getId() != null) {
					saida.setId(entrada.getId());
				}
				saida.setDescricao(entrada.getDescricao());
				saida.setValor(entrada.getValor());
				saida.setData(entrada.getData());
				saida.setCodigoUsuario(entrada.getCodigoUsuario());
			}
			return saida;
		}

	};

}
