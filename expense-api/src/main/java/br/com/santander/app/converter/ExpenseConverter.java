package br.com.santander.app.converter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.model.Category;
import br.com.santander.app.model.Expense;
import br.com.santander.app.model.User;

public class ExpenseConverter {

	public static Expense fromDTO(final ExpenseDTO dto) {
		final Expense expense= new Expense();
		final User user= new User();
		final Category category= new Category();
		expense.setUser(user);
		expense.setCategory(category);
		expense.setId(dto.getId());
		expense.getCategory().setDescription(dto.getDescription());
		expense.getUser().setId(dto.getIdUser());
		expense.setExpenseDate(dto.getExpenseDate());
		expense.setValue(dto.getValue());
		expense.setVersion(dto.getVersion());
		return expense;
	}

	public static ExpenseDTO toDTO(final Expense model) {
		final ExpenseDTO expenseDTO= new ExpenseDTO();
		expenseDTO.setId(model.getId());
		if(model.getCategory() != null) {
			expenseDTO.setDescription(model.getCategory().getDescription());
		}
		expenseDTO.setIdUser(model.getUser().getId());
		expenseDTO.setExpenseDate(model.getExpenseDate());
		expenseDTO.setValue(model.getValue());
		expenseDTO.setVersion(model.getVersion());
		return expenseDTO;
	}

	public static List<ExpenseDTO> toDTO(final List<Expense> list){
		final List<ExpenseDTO> results = new ArrayList<>();
		for (final Expense expense : list){
			results.add(toDTO(expense));
		}
		return results;
	}

	public static ExpenseDTO toDTO(final Map<String, String> params) throws ParseException {
		final ExpenseDTO dto = new ExpenseDTO();

		for (final Map.Entry<String, String> entry : params.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("idUser")) {
				dto.setIdUser(Long.parseLong(entry.getValue()));
			}
			if(entry.getKey().equalsIgnoreCase("expenseDate")) {
				final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				dto.setExpenseDate(LocalDateTime.parse(entry.getValue(), formatter));
			}
		}
		return dto;
	}
}
