package br.com.fellipeoliveira.expensemanagement.gateways.http;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import br.com.fellipeoliveira.expensemanagement.gateways.http.request.SpendingRequest;
import br.com.fellipeoliveira.expensemanagement.usecases.SpendingUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/spending", produces = APPLICATION_JSON_VALUE)
public class SpendingController {

  private final SpendingUseCase spendingUseCase;

  @GetMapping
  public ResponseEntity findSpending() {
    log.info("RECEIVED ON FIND SPENDING METHOD");
    return ResponseEntity.ok().body(spendingUseCase.findAllSpending());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity createSpent(@RequestBody final SpendingRequest spendingRequest) {
    log.info("RECEIVED ON CREATE SPENT METHOD");
    spendingUseCase.saveSpent(spendingRequest);
    return ResponseEntity.ok().body(spendingUseCase.findAllSpending());
  }
}
