package br.com.fellipeoliveira.expensemanagement.gateways.http;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.fellipeoliveira.expensemanagement.gateways.http.request.SpendingRequest;
import br.com.fellipeoliveira.expensemanagement.usecases.SpendingUseCase;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/spending", produces = APPLICATION_JSON_VALUE)
public class SpendingController {

  private final SpendingUseCase spendingUseCase;

  @GetMapping
  public ResponseEntity findAllExpenses() {
    log.info("RECEIVED ON FIND EXPENSES METHOD");
    return ResponseEntity.ok().body(spendingUseCase.findAllExpenses());
  }

  @GetMapping(value = "/filter/{expense-id}")
  public ResponseEntity findExpenseById(
      @PathVariable(value = "expense-id") final String expenseId) {
    log.info("RECEIVED ON FIND EXPENSE BY ID METHOD");
    return ResponseEntity.ok().body(spendingUseCase.findExpense(expenseId));
  }

  @GetMapping(value = "/filter")
  public ResponseEntity findExpensesByDate(
      @RequestParam(value = "data", required = false) @DateTimeFormat(iso = ISO.DATE) final LocalDate date) {
    log.info("RECEIVED ON FIND EXPENSES BY DATE METHOD");
    return ResponseEntity.ok().body(spendingUseCase.findAllExpensesByDate(date));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity createSpent(@RequestBody final SpendingRequest spendingRequest) {
    log.info("RECEIVED ON CREATE SPENT METHOD");
    spendingUseCase.saveSpent(spendingRequest);
    return ResponseEntity.ok().body(spendingUseCase.findAllExpenses());
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity updateSpent(@RequestBody final SpendingRequest spendingRequest) {
    log.info("RECEIVED ON UPDATE SPENT METHOD");
    spendingUseCase.saveSpent(spendingRequest);
    return ResponseEntity.ok().body(spendingUseCase.findAllExpenses());
  }

  @GetMapping(value = "/categories")
  public ResponseEntity findCategories(
      @RequestParam(value = "term") final String query) {
    log.info("RECEIVED ON FIND CATEGORIES METHOD");
    return ResponseEntity.ok().body(spendingUseCase.findCategories(query));
  }
}
