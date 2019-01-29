/**
 * 
 */
package br.com.adslima.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.adslima.exception.InvalidExpenseCategoryException;

/**
 * @author andrews.silva
 *
 */
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

	@Override
	public String toString() {
		return description;
	}

}
