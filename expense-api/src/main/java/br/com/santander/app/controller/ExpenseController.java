package br.com.santander.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.app.dto.ExpenseDTO;
import br.com.santander.app.exception.ExpenseError;
import br.com.santander.app.exception.ExpenseExceptionHandler;
import br.com.santander.app.service.ExpenseService;

@RequestMapping("/expenses")
@CrossOrigin
@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody final ExpenseDTO dto){
		try {
			final ExpenseDTO inserted= expenseService.insert(dto);
			return new ResponseEntity<>(inserted, HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<>(new ExpenseError(1, ExpenseExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{idUser}", method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable final Long idUser){
		try {
			return new ResponseEntity<>(expenseService.findByIdUser(idUser), HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<>(new ExpenseError(1, ExpenseExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
