package br.com.santander.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final int OPTIMISTIC_LOCK = 410;

	/*
	 * @ExceptionHandler(Exception.class) public final
	 * ResponseEntity<ExceptionResponse> handleAllExceptions(final Exception ex,
	 * final WebRequest request) { final ExceptionResponse exceptionResponse = new
	 * ExceptionResponse(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new
	 * ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

	@ExceptionHandler(OptimisticLockException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(final OptimisticLockException ex,
			final WebRequest request) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(OPTIMISTIC_LOCK, LocalDateTime.now(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}