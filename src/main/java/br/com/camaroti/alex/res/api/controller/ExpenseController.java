package br.com.camaroti.alex.res.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camaroti.alex.res.api.model.Expense;
import br.com.camaroti.alex.res.api.service.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping(path="/expenses") // Map ONLY GET Requests
	public @ResponseBody Expense add(@RequestParam int codUser
			, @RequestParam String description, @RequestParam double cost) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Expense n = new Expense();
		n.setCodUser(codUser);
		n.setDate(new Date());
		n.setDescription(description);
		n.setValue(cost);
		Expense newG = expenseService.save(n);
		return newG;
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
