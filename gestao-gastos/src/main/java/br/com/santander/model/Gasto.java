package br.com.santander.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;;

@Table(value = "gastos")
public class Gasto implements Serializable {

	private static final long serialVersionUID = 441976184757996052L;

	@JsonIgnore
	@PrimaryKeyColumn(name = "codigogasto", type = PrimaryKeyType.PARTITIONED)
	private UUID codigoGasto;
	
	@Column(value = "numerocartao")
	private Long numeroCartao;

	@Column(value = "descricao")
	private String descricao;

	@Column(value = "valor")
	private BigDecimal valor;

	@Column(value = "codigousuario")
	private Long codigoUsuario;

	@CassandraType(type=Name.TIMESTAMP)
	@Column(value = "data")
	private Date data;

	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public Gasto(UUID codigoGasto, Long numeroCartao,String descricao, BigDecimal valor, Long codigoUsuario, Date data) {
		super();
		this.codigoGasto = codigoGasto;
		this.numeroCartao = numeroCartao;
		this.descricao = descricao;
		this.valor = valor;
		this.codigoUsuario = codigoUsuario;
		this.data = data;
	}

	public Gasto() {

	}

	/**
	 * @return the codigoGasto
	 */
	public UUID getCodigoGasto() {
		return codigoGasto;
	}

	/**
	 * @param codigoGasto
	 *            the codigoGasto to set
	 */
	public void setCodigoGasto(UUID codigoGasto) {
		this.codigoGasto = codigoGasto;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the codigoUsuario
	 */
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario
	 *            the codigoUsuario to set
	 */
	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the data
	 */
	@JsonIgnore
	public Date getData() {
		return data;
	}

	@JsonAlias(value="data")
	public String getDataUTC() {
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(data);

		return utcTime;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	@JsonIgnore
	public void setData(Date data) {
		this.data = data;
	}

	public void setData(String dataUTC) {
		if (dataUTC != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			try {
				data = (Date) dateFormat.parse(dataUTC);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
