package com.santander.gestaogastos.exception;

public class GastosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public GastosException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public GastosException() {
		super();
}

}
