/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.model.entity;

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
 * Classe responsável por definir os dados da entidade Categoria.
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
@Table("categoria")
public class Categoria {
	
	@PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;
	
	/**
	 * Atributo descricao
	 */
	private String descricao;
	
	
	public Categoria() {
        id = UUID.randomUUID();
    }
	

}
