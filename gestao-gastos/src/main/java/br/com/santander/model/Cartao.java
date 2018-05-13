package br.com.santander.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;;

@Table(value = "cartoes")
public class Cartao implements Serializable {

	private static final long serialVersionUID = 8883878302678919902L;

	@PrimaryKeyColumn(name = "numerocartao", type = PrimaryKeyType.PARTITIONED)
	private Long numeroCartao;

	@Column(value = "codigousuario")
	private Long codigoUsuario;

	@CassandraType(type=Name.TIMESTAMP)
	@Column(value = "datacontrato")
	private Date dataContrato;

	public Cartao(Long numeroCartao, Long codigoUsuario, Date dataContrato) {
		super();
		this.numeroCartao = numeroCartao;
		this.codigoUsuario = codigoUsuario;
		this.dataContrato = dataContrato;
	}

	public Cartao() {

	}

	/**
	 * @return the numeroCartao
	 */
	public Long getNumeroCartao() {
		return numeroCartao;
	}

	/**
	 * @param numeroCartao the numeroCartao to set
	 */
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	/**
	 * @return the codigoUsuario
	 */
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the dataContrato
	 */
	public Date getDataContrato() {
		return dataContrato;
	}

	/**
	 * @param dataContrato the dataContrato to set
	 */
	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}
}
