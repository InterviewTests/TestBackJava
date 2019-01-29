package br.com.adslima.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import br.com.adslima.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class AddExpenseManagementCommand {

	@TargetAggregateIdentifier
	private String id;
	private Integer userCode;
	private String description;
	private LocalDateTime date;
	private BigDecimal value;
	private ExpenseCategory category;
}