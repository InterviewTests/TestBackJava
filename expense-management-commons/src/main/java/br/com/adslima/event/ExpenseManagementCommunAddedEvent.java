package br.com.adslima.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.adslima.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ExpenseManagementCommunAddedEvent {

	private String id;
	private Integer userCode;
	private String description;
	private LocalDateTime date;
	private BigDecimal value;
	private ExpenseCategory category;
}