/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe responsável por definir os dados do DTO CategoriaGastoCartao.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 15:57:01
 * @version x.x
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
public class CategoriaGastoCartaoDTO {

	private String idGastoCartao;

	private String categoria;

}
