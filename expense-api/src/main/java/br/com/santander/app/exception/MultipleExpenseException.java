package br.com.santander.app.exception;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MultipleExpenseException extends RuntimeException{

	private static final long serialVersionUID = 7139972070262446093L;

	/**
	 * List exceptions
	 */
	private final Set<ExpenseException> exceptions = new HashSet<>();

	public MultipleExpenseException(final ExpenseException message) {
		exceptions.add(message);
	}

	public MultipleExpenseException(final Collection<? extends ExpenseException> messageList) {
		exceptions.addAll(messageList);
	}

	public void addException(final ExpenseException message) {
		exceptions.add(message);
	}

	public void addExceptionList(final Collection<? extends ExpenseException> messageList) {
		exceptions.addAll(messageList);
	}

	public Set<ExpenseException> getExceptions() {
		return exceptions;
	}

	public boolean contains(final ExpenseException ex) {
		return (null != exceptions && !exceptions.isEmpty()) && exceptions.contains(ex);
	}

	public MultipleExpenseException() {
	}
}