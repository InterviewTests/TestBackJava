package br.com.santander.app.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.text.ParseException;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.santander.app.converter.ExpenseConverter;
import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.service.ExpenseService;

@RequestMapping("/santander/api/v1/expenses")
@CrossOrigin
@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody final ExpenseDTO dto){
		final ExpenseDTO savedExpense= expenseService.insert(dto);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedExpense.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody final ExpenseDTO dto){
		expenseService.update(dto);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<?> findByIdUser(@PathVariable final Long idUser){
		final ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.add(linkTo(methodOn(ExpenseController.class).update(expenseDTO)).withRel("update"));
		return new ResponseEntity<>(expenseService.findByIdUser(idUser), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findByFilter(@RequestParam final Map<String, String> params) throws ParseException{
		return new ResponseEntity<>(expenseService.findByFilter(ExpenseConverter.toDTO(params)), HttpStatus.OK);
	}
}
