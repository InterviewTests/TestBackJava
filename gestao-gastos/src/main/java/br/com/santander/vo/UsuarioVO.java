package br.com.santander.vo;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = -7033917311261954531L;

	@JsonProperty(value ="codigousuario", required = true)
	private Long codigoUsuario;
	
	@JsonProperty(value ="nome", required = true)
	private String nome;
	
	@JsonProperty(value ="password", required = true)
	private String password;
	
	@JsonProperty(value ="statusatividade", required = true)
	private boolean statusAtividade;

	@JsonProperty(value ="datacadastro", required = true)
	private Date dataCadastro;

	public UsuarioVO() {

	}

	public UsuarioVO(Long codigoUsuario, String nome, String password, boolean statusAtividade, Date dataCadastro) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nome = nome;
		this.password = password;
		this.statusAtividade = statusAtividade;
		this.dataCadastro = dataCadastro;
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