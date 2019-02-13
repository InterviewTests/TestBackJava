package br.com.adslima.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import br.com.adslima.command.AddExpenseManagementCommand;
import br.com.adslima.command.UpdateCategoryExpenseManagementCommand;
import br.com.adslima.event.ExpenseManagementCategoryCommunsUpdatedEvent;
import br.com.adslima.event.ExpenseManagementCommunAddedEvent;
import br.com.adslima.model.ExpenseCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author andrews.silva
 *
 */
@Slf4j
@Getter
@Aggregate
@NoArgsConstructor
public class ExpenseManagementAggregate {

	@AggregateIdentifier
	private String id;
	private Integer userCode;
	private String description;
	private LocalDateTime date;
	private BigDecimal value;
	private ExpenseCategory category;

	@CommandHandler
	public ExpenseManagementAggregate(AddExpenseManagementCommand cmd) {
		log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);

		Assert.hasLength(cmd.getId(), "Id não deve estar vazio ou nulo.");
		Assert.notNull(cmd.getUserCode(), "UserCode não deve estar vazio ou nulo.");
		Assert.hasLength(cmd.getDescription(), "Description não deve estar vazio ou nulo.");
		Assert.notNull(cmd.getDate(), "Date não deve estar vazio ou nulo.");
		Assert.notNull(cmd.getValue(), "Value não deve estar vazio ou nulo.");

		apply(new ExpenseManagementCommunAddedEvent(cmd.getId(), cmd.getUserCode(), cmd.getDescription(), cmd.getDate(),
				cmd.getValue(), cmd.getCategory()));

		log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
	}

	@CommandHandler
	public void handle(UpdateCategoryExpenseManagementCommand cmd) {
		log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);

		Assert.hasLength(cmd.getCardExpenseId(), "ExpenseCardId não deve estar vazio ou nulo.");
		Assert.notNull(cmd.getCategory(), "Category não deve estar vazio ou nulo.");
		
		apply(new ExpenseManagementCategoryCommunsUpdatedEvent(cmd.getCardExpenseId(), cmd.getCategory()));

		log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
	}

	@EventSourcingHandler
	public void on(ExpenseManagementCommunAddedEvent event) {
		log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);

		this.id = event.getId();
		this.userCode = event.getUserCode();
		this.description = event.getDescription();
		this.date = event.getDate();
		this.value = event.getValue();

		log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
	}

	@EventSourcingHandler
	public void on(ExpenseManagementCategoryCommunsUpdatedEvent event) {

		log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
		this.category = event.getCategory();
		log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
	}

}