package br.com.adslima.exception.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.adslima.exception.NotFoundException;
import br.com.adslima.model.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public static String getMessage(Exception e) {
		logger.error("Exception", e);
		return e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { NoHandlerFoundException.class, NotFoundException.class })
	public ErrorMessage resourceNotFoundExceptionHandler(Exception e) {
		return new ErrorMessage(getMessage(e), "404");
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorMessage methodNotSupportedException(Exception e) {
		return new ErrorMessage("Method Not Supported :" + getMessage(e), "405");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
			ServletRequestBindingException.class })
	public ErrorMessage badRequestExceptionHandler(Exception e) {
		return new ErrorMessage(getMessage(e), "400");
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = { DataIntegrityViolationException.class, ConstraintViolationException.class })
	public ErrorMessage dataIntegrityExceptionHandler(Exception e) {
		return new ErrorMessage("Constraint voilation : " + getMessage(e), "409");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { EmptyResultDataAccessException.class, EntityNotFoundException.class,
			HttpMessageNotWritableException.class })
	public ErrorMessage emptyResultDataAccessExceptionHandler(Exception e) {
		return new ErrorMessage("Entity doesnot exist : " + getMessage(e), "400");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { DataException.class, SQLException.class, HibernateException.class,
			JpaSystemException.class, JsonMappingException.class, JsonParseException.class, IOException.class })
	public ErrorMessage systemException(Exception e) {
		return new ErrorMessage("System error : " + getMessage(e), "500");
	}

}
