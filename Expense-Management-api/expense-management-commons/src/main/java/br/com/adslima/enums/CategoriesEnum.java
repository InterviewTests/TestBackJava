/**
 * 
 */
package br.com.adslima.enums;

import lombok.Getter;

/**
 * @author andrews.silva
 *
 */
@Getter
public enum CategoriesEnum {

	VESTUARIO(1, "Vestuário"), ELETRONICOS(2, "Eletrônicos"), SUPERMERCADO(3, "Supermercado"), TRANSPORTE(4,
			"Transporte"), VIAGEM(5, "Viagem"), OUTROS(6, "Outros"), SERVICOS(7, "Serviços"), SAUDE(8,
					"Saúde"), CASA(9, "Vesturario"), RESTAURANTE(10,
							"Restaurante"), EDUCACAO(11, "Educação"), LAZER(12, "Lazer");

	private String description;
	private int id;

	private CategoriesEnum() {

	}

	/**
	 * @param description
	 */
	private CategoriesEnum(int id, String description) {
		this.description = description;
		this.id = id;
	}

}
