package br.com.santander.app.exception;

public class ExpenseException extends Exception{

	private static final long serialVersionUID = 1L;

	public ExpenseException() {
	}

	public ExpenseException(final String message) {
		super(message);
	}

	public ExpenseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ExpenseException(final Throwable cause) {
		super(cause);
	}

}
