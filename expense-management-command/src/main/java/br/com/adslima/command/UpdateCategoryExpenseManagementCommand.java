package br.com.adslima.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import br.com.adslima.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @author andrews.silva
 *
 */
@Getter
@ToString
@AllArgsConstructor
public class UpdateCategoryExpenseManagementCommand {

	@TargetAggregateIdentifier
	private String cardExpenseId;
	private ExpenseCategory category;
}
