/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Classe responsável por definir os dados da entidade GastoCartao.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 15:57:01
 * @version x.x
 */
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
@Table("gastocartao")
public class GastoCartao {
	
	@PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
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
	 * Atributo idCategoria
	 */
	private UUID idCategoria;
	
	public GastoCartao() {
        id = UUID.randomUUID();
    }
	

}
