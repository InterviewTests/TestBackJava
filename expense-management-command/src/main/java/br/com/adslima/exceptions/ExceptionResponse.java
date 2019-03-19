package br.com.adslima.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author andrews.silva
 *
 */
@Getter
public class ExceptionResponse {

	private final int code;
	private final LocalDateTime timestamp;
	private final String message;
	private final String details;

	public ExceptionResponse(final int code, final LocalDateTime timestamp, final String message,
			final String details) {
		super();
		this.code = code;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
}