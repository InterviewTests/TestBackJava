package br.com.adslima.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.adslima.response.Response;
import br.com.adslima.command.AddExpenseManagementCommand;
import br.com.adslima.command.UpdateCategoryExpenseManagementCommand;
import br.com.adslima.dto.ExpenseManagementCommunsDTO;
import br.com.adslima.exception.ExpenseManagementNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping("/api-command")
@AllArgsConstructor
public class ExpenseManagementController {

	@Autowired
	CommandGateway commandGateway;

	/**
	 * 
	 * @param dto
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response<AddExpenseManagementCommand>> create(
			@RequestBody final ExpenseManagementCommunsDTO dto, final BindingResult result) {

		AddExpenseManagementCommand command = new AddExpenseManagementCommand(UUID.randomUUID().toString(),
				dto.getUserCode(), dto.getDescription(), dto.getDate(), dto.getValue(), dto.getCategory());

		log.info("Executing command: {}", command.toString());

		final Response<AddExpenseManagementCommand> response = new Response<AddExpenseManagementCommand>();

		if (result.hasErrors()) {

			log.error("Error adding expense by cards: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.commandGateway.send(command);
		response.setData(command);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}/categories")
	public ResponseEntity<Response<UpdateCategoryExpenseManagementCommand>> updateCategory(
			@PathVariable final String id, @RequestBody final ExpenseManagementCommunsDTO dto,
			final BindingResult result) {

		UpdateCategoryExpenseManagementCommand command = new UpdateCategoryExpenseManagementCommand(id,
				dto.getCategory());

		log.info("Executing command: {}", command.toString());

		final Response<UpdateCategoryExpenseManagementCommand> response = new Response<UpdateCategoryExpenseManagementCommand>();

		if (result.hasErrors()) {
			log.error("error while updating card spend category: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		commandGateway.send(command);
		response.setData(command);

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(AggregateNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}

	@ExceptionHandler(ExpenseManagementNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String ExpenseCategorNotFound(ExpenseManagementNotFoundException exception) {
		return exception.getMessage();
	}
}