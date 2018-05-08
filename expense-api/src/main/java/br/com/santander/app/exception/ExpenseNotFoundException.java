package br.com.santander.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 703949687169753379L;

	public ExpenseNotFoundException(final String exception) {
		super(exception);
	}
}
