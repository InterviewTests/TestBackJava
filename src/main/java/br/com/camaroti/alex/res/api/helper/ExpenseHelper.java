package br.com.camaroti.alex.res.api.helper;

import java.util.Date;

import br.com.camaroti.alex.res.api.domain.Category;
import br.com.camaroti.alex.res.api.domain.Expense;

public class ExpenseHelper {

	public static Expense convertExpense(int codUser, String description, double cost, String category) {
		Expense expense = new Expense();
		Category categoryObj = new Category();
		expense.setCodUser(codUser);
		expense.setDate(new Date());
		expense.setDescription(description);
		expense.setValue(cost);
		categoryObj.setName(category);
		expense.setCategory(categoryObj);
		return expense;
	}
	
}
