package br.com.camaroti.alex.res.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camaroti.alex.res.api.model.Category;
import br.com.camaroti.alex.res.api.model.Expense;
import br.com.camaroti.alex.res.api.service.CategoryService;
import br.com.camaroti.alex.res.api.service.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(path="/expenses") // Map ONLY POST Request
	public @ResponseBody Expense addExpense(@RequestParam int codUser
			, @RequestParam String description, @RequestParam double cost, @RequestParam(value = "category", required = false) String category) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Expense expense = new Expense();
		expense.setCodUser(codUser);
		expense.setDate(new Date());
		expense.setDescription(description);
		expense.setValue(cost);
		checkIfParamIsNull(category, expense);
		return expenseService.save(expense);
	}
	
	@PutMapping(path="/expenses") // Map ONLY PUT Request
	public @ResponseBody Expense add(@RequestParam int cod, @RequestParam int codUser
			, @RequestParam String description, @RequestParam double cost, @RequestParam(value = "category", required = false) String category) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Expense expense = new Expense();
		expense.setCod(cod);
		expense.setCodUser(codUser);
		expense.setDate(new Date());
		expense.setDescription(description);
		expense.setValue(cost);
		checkIfParamIsNull(category, expense);
		return expenseService.update(expense);
	}

	private void checkIfParamIsNull(String category, Expense expense) {
		if(category != null && !category.isEmpty()) {
			saveCategoryIfNotExists(category, expense);
		} else {
			expense.setCategory(null);
		}
	}

	private void saveCategoryIfNotExists(String category, Expense expense) {
		Category categoryObj = categoryService.findByName(category);
		if(categoryObj != null) {
			expense.setCategory(categoryObj);
		} else {
			Category newCategory = new Category(category);
			categoryService.save(newCategory);
			expense.setCategory(newCategory);
		}
	}

	@RequestMapping(path="/expenses")
	public @ResponseBody Iterable<Expense> getAll() {
		return expenseService.findAll();
	}
	
	@GetMapping(path="/expenses/{codUser}")
	public @ResponseBody Iterable<Expense> findByCodUserOrderByDateDesc(@PathVariable int codUser) {
		return expenseService.findByCodUserOrderByDateDesc(codUser);
	}
	
	@GetMapping(path="/expenses/{codUser}/{date}")
	public @ResponseBody Iterable<Expense> findByCodUserAndDateOrderByDateDesc(@PathVariable int codUser, 
			@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Long date) {
		Date start = new Date(date);
		Date end = new Date(date + 86399999L);
		System.out.println(start);
		System.out.println(end);
		return expenseService.findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}
	

}
