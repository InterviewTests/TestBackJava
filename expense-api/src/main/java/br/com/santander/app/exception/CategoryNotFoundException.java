package br.com.santander.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5496341680698981027L;

	public CategoryNotFoundException(final String exception) {
		super(exception);
	}
}
