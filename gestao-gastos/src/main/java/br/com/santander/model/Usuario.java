package br.com.santander.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;;

@Table(value = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2533270303407974740L;

	@PrimaryKeyColumn(name = "codigousuario", type = PrimaryKeyType.PARTITIONED)
	private Long codigoUsuario;
	
	@Column(value = "nome")
	private String nome;
	
	@Column(value = "password")
	private String password;
	
	@Column(value = "statusatividade")
	private boolean statusAtividade;

	@CassandraType(type=Name.TIMESTAMP)
	@Column(value = "datacadastro")
	private Date dataCadastro;

	public Usuario(Long codigoUsuario, String nome, String password, boolean statusAtividade, Date dataCadastro) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nome = nome;
		this.password = password;
		this.statusAtividade = statusAtividade;
		this.dataCadastro = dataCadastro;
	}

	public Usuario() {

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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the statusAtividade
	 */
	public boolean isStatusAtividade() {
		return statusAtividade;
	}

	/**
	 * @param statusAtividade the statusAtividade to set
	 */
	public void setStatusAtividade(boolean statusAtividade) {
		this.statusAtividade = statusAtividade;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
