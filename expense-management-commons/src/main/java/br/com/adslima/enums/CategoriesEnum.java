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

	VESTUÁRIO(1, "Vesturario"), ELETRÔNICOS(2, "Vesturario"), SUPERMERCADO(3, "Vesturario"), TRANSPORTE(4,
			"Vesturario"), VIAGEM(5, "Vesturario"), OUTROS(6, "Vesturario"), SERVIÇOS(7, "Vesturario"), SAÚDE(8,
					"Vesturario"), CASA(9, "Vesturario"), RESTAURANTE(10,
							"Vesturario"), EDUCAÇÃO(11, "Vesturario"), LAZER(12, "Vesturario");

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