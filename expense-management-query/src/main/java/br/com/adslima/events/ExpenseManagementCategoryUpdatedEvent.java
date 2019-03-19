package br.com.adslima.events;

import br.com.adslima.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author andrews.silva
 *
 */
@Getter
@ToString
@AllArgsConstructor
public class ExpenseManagementCategoryUpdatedEvent {

	private String cardExpenseId;
	private ExpenseCategory category;
}