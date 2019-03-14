package br.com.adslima.components;

import java.util.List;
import java.util.Optional;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import br.com.adslima.events.ExpenseManagementAddedEvent;
import br.com.adslima.events.ExpenseManagementCategoryUpdatedEvent;
import br.com.adslima.exception.ExpenseManagementNotFoundException;
import br.com.adslima.model.Category;
import br.com.adslima.model.ExpenseCategory;
import br.com.adslima.model.ExpenseManagement;
import br.com.adslima.repository.CategoryRepository;
import br.com.adslima.repository.ExpenseManagementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author andrews.silva
 *
 */
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
	private final CategoryRepository categoryRepository;

	/**
	 * Evento responsavel por adicionar um gasto com cartão.
	 * 
	 * @param event
	 */
	@EventHandler
	public void on(final ExpenseManagementAddedEvent event) {
		
		List<Category> listCategories = this.categoryRepository.findByExpenseDescription(event.getDescription());

		if(listCategories != null){
			
		Optional<Category> cat = listCategories.stream().filter(c -> c.getCategoryDescription() != null).findFirst();

		if (cat.isPresent()) {

			Category category = getCategorySolr(event);
			category.setCategoryDescription(cat.get().getCategoryDescription());

			Category catergoriesSorl = this.categoryRepository.save(category);
			log.info("Um gasto no cartão foi adicionado ao Solr {}", catergoriesSorl.toString());

			event.setCategory(new ExpenseCategory(cat.get().getCategoryDescription()));
			ExpenseManagement expenseManagement = this.repository
					.save(new ExpenseManagement(event.getId(), event.getUserCode(), event.getDescription(),
							event.getDate(), event.getValue(), event.getCategory()));
			log.info("Um gasto com cartão foi adcionado! {}", expenseManagement.toString());
		}
		} else {

			Category category = getCategorySolr(event);
			Category expenseSorl = this.categoryRepository.save(category);

			log.info("Um gasto no cartão foi adicionado ao Solr {}", expenseSorl.toString());

			ExpenseManagement expenseManagement = this.repository
					.save(new ExpenseManagement(event.getId(), event.getUserCode(), event.getDescription(),
							event.getDate(), event.getValue(), event.getCategory()));

			log.info("Um gasto com cartão foi adcionado! {}", expenseManagement.toString());
		}
	}

	/**
	 * Evento responsavel por atualizar categoria
	 * 
	 * @param event
	 */
	@EventHandler
	public void on(ExpenseManagementCategoryUpdatedEvent event) {

		ExpenseManagement expenseCard = this.repository.findById(event.getCardExpenseId())
				.orElseThrow(ExpenseManagementNotFoundException::new);

		expenseCard.setCategory(event.getCategory());
		this.repository.save(expenseCard);

		Category category = this.categoryRepository.findByCategoryId(event.getCardExpenseId());
		category.setCategoryDescription(event.getCategory().getDescription());
		this.categoryRepository.save(category);

		log.info("Uma categoria foi atualizada! {}", expenseCard);
	}

	/**
	 * TODO renomear esses metodos para nomes melhores.
	 * 
	 * @param event
	 * @return
	 */
	private Category getCategorySolr(ExpenseManagementAddedEvent event) {
		Category category = new Category();
		category.setCategoryId(event.getId());
		category.setExpenseDescription(event.getDescription());
		return category;
	}

}
