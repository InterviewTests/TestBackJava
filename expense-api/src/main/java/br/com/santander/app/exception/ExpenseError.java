package br.com.santander.app.exception;

import java.io.Serializable;
import java.util.List;

public class ExpenseError implements Serializable {

	private static final long serialVersionUID = 8467553072140041374L;

	private int code;
	private List<String> messages;

	public ExpenseError(final int cod, final List<String> messages) {
		super();
		this.code = cod;
		this.messages = messages;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(final List<String> messages) {
		this.messages = messages;
	}
}