package br.com.adslima.exception;

/**
 * 
 * @author andrews.silva
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 2827308907740826575L;

	private String code;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
