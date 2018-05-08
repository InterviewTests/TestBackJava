package br.com.santander.app.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.model.Category;
import br.com.santander.app.model.Expense;

public class ExpenseConverter {

	public static Expense fromDTO(final ExpenseDTO dto) {
		final Expense expense= new Expense();
		final Category category= new Category();
		expense.setCategory(category);
		expense.setId(dto.getCode());
		expense.getCategory().setDescription(dto.getDescription());
		expense.setUserCode(dto.getUserCode());
		expense.setExpenseDate(dto.getDate());
		expense.setValue(dto.getValue());
		expense.setVersion(dto.getVersion());
		return expense;
	}

	public static ExpenseDTO toDTO(final Expense model) {
		final ExpenseDTO expenseDTO= new ExpenseDTO();
		expenseDTO.setCode(model.getId());
		if(model.getCategory() != null) {
			expenseDTO.setDescription(model.getCategory().getDescription());
		}
		expenseDTO.setUserCode(model.getUserCode());
		expenseDTO.setDate(model.getExpenseDate());
		expenseDTO.setValue(model.getValue());
		expenseDTO.setVersion(model.getVersion());
		return expenseDTO;
	}

	public static List<ExpenseDTO> toDTO(final List<Expense> listModel){
		final List<ExpenseDTO> results = listModel.stream().map(model -> {
			return toDTO(model);
		}).collect(Collectors.toList());
		return results;
	}
}
