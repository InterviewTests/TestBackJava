/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.exception;

/**
 * The GastoCartaoException.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:44:32
 * @version x.x
 */
public class GastoCartaoException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe GastoCartaoException
	 */
	public GastoCartaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe GastoCartaoException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public GastoCartaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe GastoCartaoException
	 *
	 * @param message
	 * @param cause
	 */
	public GastoCartaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe GastoCartaoException
	 *
	 * @param message
	 */
	public GastoCartaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe GastoCartaoException
	 *
	 * @param cause
	 */
	public GastoCartaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
