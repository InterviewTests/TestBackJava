package br.com.adslima.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.adslima.exceptions.InvalidExpenseCategoryException;
import lombok.Data;

/**
 * @author andrews.silva
 *
 */
@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ExpenseCategory {

	private String description;

	public ExpenseCategory(String category) {
		if (!isValid(category))
			throw new InvalidExpenseCategoryException("Invalid Expense Category");
		this.description = category;
	}

	public static ExpenseCategory of(String category) {
		return new ExpenseCategory(category);
	}

	private boolean isValid(String category) {
		return category != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}

}
