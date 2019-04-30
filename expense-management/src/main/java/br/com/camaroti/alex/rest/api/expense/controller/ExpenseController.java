package br.com.camaroti.alex.rest.api.expense.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camaroti.alex.rest.api.expense.domain.Expense;
import br.com.camaroti.alex.rest.api.expense.helper.ExpenseHelper;
import br.com.camaroti.alex.rest.api.expense.service.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping(path="/expenses")
	public @ResponseBody Expense addExpense(@RequestParam int codUser
			, @RequestParam String description, @RequestParam double cost, @RequestParam(value = "category", required = false) String category) throws Exception {
		Expense expense = ExpenseHelper.convertExpense(codUser, description, cost, category);
		return expenseService.save(expense);
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
		return expenseService.findByCodUserAndDateBetweenOrderByDateDesc(codUser, start, end);
	}
	

}
