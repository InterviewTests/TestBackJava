package br.com.santander.app.exception;

import java.util.ArrayList;
import java.util.List;

public class ExpenseExceptionHandler {

	public static List<String> getExcetionError(final Exception e){
		final List<String> excessoes= new ArrayList<>();
		if (e instanceof MultipleExpenseException) {
			final MultipleExpenseException me = (MultipleExpenseException) e;
			for (final ExpenseException exception : me.getExceptions()) {
				excessoes.add(exception.getMessage());
			}
		}else if (e instanceof ExpenseException){
			final ExpenseException exception = (ExpenseException) e;
			excessoes.add(exception.getMessage());
		}else{
			excessoes.add("erro generico");
		}
		return excessoes;
	}
}
