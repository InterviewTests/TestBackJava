package br.com.adslima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ExpenseManagementNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseManagementNotFoundException(final String exception) {
		super(exception);
	}
}
