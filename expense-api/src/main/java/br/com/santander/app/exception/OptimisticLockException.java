package br.com.santander.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OptimisticLockException extends RuntimeException{

	private static final long serialVersionUID = -8025745487826317790L;

	public OptimisticLockException(final String exception) {
		super(exception);
	}

}
