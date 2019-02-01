package br.com.adslima.components;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.adslima.event.ExpenseManagementCategoryCommunsUpdatedEvent;
import br.com.adslima.event.ExpenseManagementCommunAddedEvent;
import br.com.adslima.exception.ExpenseManagementNotFoundException;
import br.com.adslima.model.Category;
import br.com.adslima.model.ExpenseManagement;
import br.com.adslima.repository.CategoryRepository;
import br.com.adslima.repository.ExpenseManagementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
@ProcessingGroup("amqpEvents")
public class ExpenseManagementEventProcessor {

	/**
	 * 
	 */
	private final ExpenseManagementRepository repository;

	/**
	 * 
	 */
	@Resource
	private final CategoryRepository categoryRepository;

	/**
	 * Evento responsavel por adicionar um gasto com cartão.
	 * 
	 * @param event
	 */
	@EventHandler
	public void on(final ExpenseManagementCommunAddedEvent event) {

		List<Category> categories = this.categoryRepository
				.findByExpenseDescription(event.getDescription(), PageRequest.of(1, 2)).getContent();

		if (!categories.isEmpty() && categories.get(0).getCategoryDescription() != null) {

			categories.forEach(cat -> {
				if (cat.getCategoryDescription() != null) {
					event.setCategory(categories.get(0).getCategoryDescription());
					Category category = getCategorySolr(event);

					Category CatergoriesSorl = this.categoryRepository.save(category);
					log.info("An expense with card was added in Solr {}", CatergoriesSorl.toString());

					ExpenseManagement expenseManagement = this.repository
							.save(new ExpenseManagement(event.getId(), event.getUserCode(), event.getDescription(),
									event.getDate(), event.getValue(), event.getCategory()));
					log.info("An expense with card was added! {}", expenseManagement.toString());

				}
			});

		} else {

			Category category = getCategorySolr(event);
			Category expenseSorl = this.categoryRepository.save(category);

			log.info("An expense with card was added in Solr {}", expenseSorl.toString());

			ExpenseManagement expenseManagement = this.repository
					.save(new ExpenseManagement(event.getId(), event.getUserCode(), event.getDescription(),
							event.getDate(), event.getValue(), event.getCategory()));

			log.info("An expense with card was added! {}", expenseManagement.toString());
		}
	}

	/**
	 * Evento responsavel por atualizar categoria
	 * 
	 * @param event
	 */
	@EventHandler
	public void on(ExpenseManagementCategoryCommunsUpdatedEvent event) {

		ExpenseManagement expenseCard = this.repository.findById(event.getCardExpenseId())
				.orElseThrow(ExpenseManagementNotFoundException::new);

		expenseCard.setCategory(event.getCategory());
		this.repository.save(expenseCard);

		Category category = this.categoryRepository.findByCategoryId(event.getCardExpenseId());
		category.setCategoryDescription(event.getCategory());
		this.categoryRepository.save(category);

		log.info("A Card Expense Category has been updated! {}", expenseCard);
	}

	/**
	 * TODO renomear esses metodos para nomes melhores.
	 * 
	 * @param event
	 * @return
	 */
	private Category getCategorySolr(ExpenseManagementCommunAddedEvent event) {
		Category category = new Category();
		category.setCategoryId(event.getId());
		category.setExpenseDescription(event.getDescription());
		category.setCategoryDescription(event.getCategory());
		return category;
	}

}
