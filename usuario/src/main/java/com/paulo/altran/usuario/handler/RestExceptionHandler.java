package com.paulo.altran.usuario.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.paulo.altran.usuario.dto.ErrorRetornoDTO;
import com.paulo.altran.usuario.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorRetornoDTO dto = ErrorRetornoDTO.builder().timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value()).title("Resource not found").detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}

}
