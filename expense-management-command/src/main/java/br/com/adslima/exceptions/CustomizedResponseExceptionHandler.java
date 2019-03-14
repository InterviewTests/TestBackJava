package br.com.adslima.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author andrews.silva
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static final int BAD_REQUEST = 400;

	@ExceptionHandler(InvalidExpenseCategoryException.class)
	public final ResponseEntity<ExceptionResponse> handleExpenseNotFoundException(
			final InvalidExpenseCategoryException ex, final WebRequest request) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST, LocalDateTime.now(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}