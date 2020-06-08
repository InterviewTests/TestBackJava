package com.paulo.altran.gasto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BussinessException extends RuntimeException {

	public BussinessException(String messsage) {
		super(messsage);
	}
}
