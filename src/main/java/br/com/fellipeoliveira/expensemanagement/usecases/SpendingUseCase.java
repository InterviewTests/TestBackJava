package br.com.fellipeoliveira.expensemanagement.usecases;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import br.com.fellipeoliveira.expensemanagement.gateways.SpendingGateway;
import br.com.fellipeoliveira.expensemanagement.gateways.http.request.SpendingRequest;
import br.com.fellipeoliveira.expensemanagement.gateways.http.response.SpendingResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpendingUseCase {

  private final SpendingGateway spendingGateway;

  public List<SpendingResponse> findAllSpending() {
    return spendingResponseBuilder(spendingGateway.findAllSpending());
  }

  public void saveSpent(SpendingRequest spendingRequest) {
    spendingGateway.save(spendingBuilder(spendingRequest));
  }

  private Spending spendingBuilder(SpendingRequest spendingRequest) {
    return Spending.builder()
        .description(spendingRequest.getDescription())
        .date(spendingRequest.getDate())
        .userCode(spendingRequest.getUserCode())
        .value(spendingRequest.getValue())
        .build();
  }

  private List<SpendingResponse> spendingResponseBuilder(List<Spending> spending) {
    return spending.stream().map(spent -> SpendingResponse.builder()
        .description(spent.getDescription())
        .userCode(spent.getUserCode())
        .date(spent.getDate())
        .value(spent.getValue())
        .build()).collect(Collectors.toList());
  }

}
