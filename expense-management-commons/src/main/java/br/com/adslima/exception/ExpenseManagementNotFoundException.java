package br.com.adslima.exception;

public class ExpenseManagementNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ExpenseManagementNotFoundException(String message) {
		super(message);
	}
}
