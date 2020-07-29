/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.exception;

/**
 * The CategoriaException.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:44:32
 * @version x.x
 */
public class CategoriaException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe CategoriaException
	 */
	public CategoriaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe CategoriaException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CategoriaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe CategoriaException
	 *
	 * @param message
	 * @param cause
	 */
	public CategoriaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe CategoriaException
	 *
	 * @param message
	 */
	public CategoriaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe CategoriaException
	 *
	 * @param cause
	 */
	public CategoriaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
