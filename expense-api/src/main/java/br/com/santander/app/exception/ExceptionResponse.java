package br.com.santander.app.exception;
import java.time.LocalDateTime;

public class ExceptionResponse {

	private final int code;
	private final LocalDateTime timestamp;
	private final String message;
	private final String details;

	public ExceptionResponse(final int code, final LocalDateTime timestamp, final String message, final String details) {
		super();
		this.code = code;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public int getCode() {
		return code;
	}
}