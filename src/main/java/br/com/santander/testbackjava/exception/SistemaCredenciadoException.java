/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.exception;

/**
 * The SistemaCredenciadoException.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 18:53:52
 * @version x.x
 */
public class SistemaCredenciadoException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe SistemaCredenciadoException
	 */
	public SistemaCredenciadoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe SistemaCredenciadoException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SistemaCredenciadoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe SistemaCredenciadoException
	 *
	 * @param message
	 * @param cause
	 */
	public SistemaCredenciadoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe SistemaCredenciadoException
	 *
	 * @param message
	 */
	public SistemaCredenciadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe SistemaCredenciadoException
	 *
	 * @param cause
	 */
	public SistemaCredenciadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
