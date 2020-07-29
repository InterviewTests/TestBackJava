/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.exception;

/**
 * The AutenticacaoException.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 18:53:52
 * @version x.x
 */
public class AutenticacaoException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe AutenticacaoException
	 */
	public AutenticacaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe AutenticacaoException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AutenticacaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe AutenticacaoException
	 *
	 * @param message
	 * @param cause
	 */
	public AutenticacaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe AutenticacaoException
	 *
	 * @param message
	 */
	public AutenticacaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe AutenticacaoException
	 *
	 * @param cause
	 */
	public AutenticacaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
