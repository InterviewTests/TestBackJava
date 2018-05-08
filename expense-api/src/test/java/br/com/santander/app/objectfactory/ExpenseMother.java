package br.com.santander.app.objectfactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.model.Expense;

/**
 * Classe utilizada nas classes de teste com a finalidade de criacao das
 * entidades reutilizáveis Ver: http://martinfowler.com/bliki/ObjectMother.html
 */
@Component
public class ExpenseMother {

	public static final String DESCRIPTION_DTO_TEST = "Description Category";

	public static Expense getExpenseModelPattern() {
		final Expense expense = new Expense();
		expense.setId(1L);
		expense.setCategory(CategoryMother.getCategoryModelWithIdPattern());
		expense.setExpenseDate(LocalDateTime.of(2018, 5, 1, 0, 0, 0));
		expense.setUserCode(1L);
		expense.setValue(20);
		expense.setVersion(0);
		return expense;
	}

	public static ExpenseDTO getExpenseDTOPattern() {
		final ExpenseDTO dto = new ExpenseDTO();
		dto.setCode(1L);
		dto.setDescription(DESCRIPTION_DTO_TEST);
		dto.setDate(LocalDateTime.of(2018, 5, 1, 0, 0, 0));
		dto.setUserCode(1L);
		dto.setValue(20);
		dto.setVersion(0);
		return dto;
	}

	public static List<Expense> getListExpenseModelPattern() {
		final List<Expense> list = new ArrayList<>();
		list.add(getExpenseModelPattern());
		return list;
	}
}
