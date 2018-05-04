package br.com.santander.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.service.ExpenseService;

@RequestMapping("/santander/api/v1/expenses")
@CrossOrigin
@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody final ExpenseDTO dto){
		final ExpenseDTO savedExpense= expenseService.insert(dto);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedExpense.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody final ExpenseDTO dto){
		expenseService.update(dto);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userCode}")
	public ResponseEntity<?> findExpensesByUserCode(@PathVariable final Long userCode){
		return new ResponseEntity<>(expenseService.findExpensesByUserCode(userCode), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> findExpensesByFilter(final ExpenseDTO dto){
		return new ResponseEntity<>(expenseService.findExpensesByFilter(dto), HttpStatus.OK);
	}
}
