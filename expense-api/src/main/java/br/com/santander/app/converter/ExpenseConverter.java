package br.com.santander.app.converter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.model.Expense;
import br.com.santander.app.model.User;

public class ExpenseConverter {

	public static Expense fromDTO(final ExpenseDTO expenseDTO) {
		final Expense expense= new Expense();
		final User user= new User();
		expense.setUser(user);
		expense.setId(expenseDTO.getId());
		expense.setDescription(expenseDTO.getDescription());
		expense.getUser().setId(expenseDTO.getIdUser());
		expense.setExpenseDate(expenseDTO.getExpenseDate());
		expense.setValue(expenseDTO.getValue());
		return expense;
	}

	public static ExpenseDTO toDTO(final Expense expense) {
		final ExpenseDTO expenseDTO= new ExpenseDTO();
		expenseDTO.setId(expense.getId());
		expenseDTO.setDescription(expense.getDescription());
		expenseDTO.setIdUser(expense.getUser().getId());
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
