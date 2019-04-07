package br.com.camaroti.alex.rest.api.expense.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.data.redis.core.RedisHash;

import br.com.camaroti.alex.rest.api.expense.client.CategoryClient;
import br.com.camaroti.alex.rest.api.expense.repository.ExpenseRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RedisHash("expense")
public @Data class Expense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Expense() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	private String description;
	private double value;
	private int codUser;
	private Date date;
	@ManyToOne(optional = true)
	private Category category;

	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private ExpenseRepository expenseRepository;

	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private CategoryClient categoryClient;

	
	public Expense(ExpenseRepository expenseRepository, CategoryClient categoryClient) {
		this.expenseRepository = expenseRepository;
		this.categoryClient = categoryClient;
	}
	
	
	public Expense save(Expense expense) throws Exception {
		checkCategoryInformation(expense);
		return expenseRepository.save(expense);

	}

	public Expense update(Expense expense) {
		return expenseRepository.save(expense);
	}

	public void remove(int id) {
		expenseRepository.deleteById(id);
	}

	public Optional<Expense> findById(int id) {
		return expenseRepository.findById(id);
	}

	public Iterable<Expense> findAll() {
		return expenseRepository.findAll();
	}

	public List<Expense> findByCodUserOrderByDateDesc(int codUser) {
		return expenseRepository.findByCodUserOrderByDateDesc(codUser);
	}

	public List<Expense> findByCodUserAndDateBetweenOrderByDateDesc(int codUser, Date start, Date end) {
		return expenseRepository.findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}

	public Expense findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(
			String description) {
		return expenseRepository
				.findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(description);
	}

	private void checkCategoryInformation(Expense expense) throws Exception {
		String category = expense.getCategory().getName();
		if (category != null && !category.isEmpty()) {
			saveCategoryIfNotExists(category, expense);
		} else {
			// Category is null, define automatically a category by a similar description
			setSameCategoryBySimilarExpenseDescription(expense);
		}
	}

	private void setSameCategoryBySimilarExpenseDescription(Expense expense) {
		Expense similarExpense = expenseRepository
				.findFirstByDescriptionContainingIgnoreCaseAndCategoryNotNullOrderByDateDesc(expense.getDescription());
		if (similarExpense != null) {
			expense.setCategory(similarExpense.getCategory());
		} else {
			expense.setCategory(null);
		}
	}

	private void saveCategoryIfNotExists(String category, Expense expense) throws Exception {
		Category categoryObj = categoryClient.findByNameIgnoreCase(category);
		if (categoryObj != null) {
			expense.setCategory(categoryObj);
		} else {
			// Category not found. Try to set category By similar Description
			setSameCategoryBySimilarExpenseDescription(expense);
			// If you dont find a similar description, just add a new Category
			if (expense.getCategory() == null) {
				Category newCategory = categoryClient.save(category);
				expense.setCategory(newCategory);
			}
		}
	}

}
