/**
 *
 */
package br.com.santander.protocolo;

import java.io.Serializable;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018
 */
public class ErrorVO implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = -7789500434984845417L;

	private Long numero;

	private String texto;

	public ErrorVO() {
	}

	public ErrorVO(final Long numero) {
		this.setNumero(numero);
	}

	public ErrorVO(final Long numero, final String texto) {
		this.setNumero(numero);
		this.setTexto(texto);
	}

	public ErrorVO(final String texto) {
		this.setTexto(texto);
	}

	/**
	 * @return the numero
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(final Long numero) {
		this.numero = numero;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(final String texto) {
		this.texto = texto;
	}

}
