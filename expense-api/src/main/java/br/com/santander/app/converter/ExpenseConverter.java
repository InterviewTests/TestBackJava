package br.com.santander.app.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.model.Expense;

public class ExpenseConverter {

	public static Expense fromDTO(final ExpenseDTO expenseDTO) {
		final Expense expense= new Expense();
		expense.setId(expenseDTO.getId());
		expense.setDescription(expenseDTO.getDescription());
		expense.setUserCode(expenseDTO.getUserCode());
		expense.setExpenseDate(expenseDTO.getExpenseDate());
		expense.setValue(expenseDTO.getValue());
		return expense;
	}

	public static ExpenseDTO toDTO(final Expense expense) {
		final ExpenseDTO expenseDTO= new ExpenseDTO();
		expenseDTO.setId(expense.getId());
		expenseDTO.setDescription(expense.getDescription());
		expenseDTO.setUserCode(expense.getUserCode());
		expenseDTO.setExpenseDate(expense.getExpenseDate());
		expenseDTO.setValue(expense.getValue());
		return expenseDTO;
	}

	public static List<ExpenseDTO> toDTO(final List<Expense> list){
		final List<ExpenseDTO> results = new ArrayList<>();
		for (final Expense expense : list){
			results.add(toDTO(expense));
		}
		return results;
	}
}
