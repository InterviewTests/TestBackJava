/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe responsável por definir os dados do DTO GastoCartao.
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
public class GastoCartaoDTO {
	
	private UUID id;
	
	/**
	 * Atributo descricao
	 */
	private String descricao;
	
	/**
	 * Atributo valor
	 */
	private BigDecimal valor;
	
	/**
	 * Atributo codigoUsuario
	 */
	private Long codigoUsuario;
	
	/**
	 * Atributo data
	 */
	private Date data;
	
	/**
	 * Atributo categoria
	 */
	private CategoriaDTO categoria;
	

}
