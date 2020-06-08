package com.paulo.altran.categoria.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.paulo.altran.categoria.dto.ErrorRetornoDTO;
import com.paulo.altran.categoria.exception.BussinessException;
import com.paulo.altran.categoria.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorRetornoDTO dto = ErrorRetornoDTO.builder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value())
				.title("Resource not found")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build();
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<?> handleBussinessException(BussinessException ex) {
		ErrorRetornoDTO dto = ErrorRetornoDTO.builder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Bussiness rule")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build();
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
	
	  
	
}
