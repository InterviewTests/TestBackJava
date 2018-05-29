/**
 *
 */
package br.com.santander.protocolo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class RetornoVO implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = 6445495486028976124L;

	private String token;

	private Boolean sucesso;

	private final List<ErrorVO> errors = new ArrayList<ErrorVO>();

	/**
	 * @return the errors
	 */
	public List<ErrorVO> getErrors() {
		return errors;
	}

	/**
	 * @return the sucesso
	 */
	public Boolean getSucesso() {
		return sucesso;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param sucesso
	 *            the sucesso to set
	 */
	public void setSucesso(final Boolean sucesso) {
		this.sucesso = sucesso;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(final String token) {
		this.token = token;
	}

}
