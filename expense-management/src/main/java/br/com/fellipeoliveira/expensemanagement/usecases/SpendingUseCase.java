package br.com.fellipeoliveira.expensemanagement.usecases;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import br.com.fellipeoliveira.expensemanagement.gateways.SpendingGateway;
import br.com.fellipeoliveira.expensemanagement.gateways.http.request.SpendingRequest;
import br.com.fellipeoliveira.expensemanagement.gateways.http.response.SpendingResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpendingUseCase {

  private final SpendingGateway spendingGateway;

  public List<SpendingResponse> findAllExpenses(Long userCode) {
    return spendingListResponseBuilder(spendingGateway.findExpensesByUserCode(userCode));
  }

  public List<SpendingResponse> findAllExpensesByUserCodeAndDate(Long userCode, LocalDate date) {
    return spendingListResponseBuilder(spendingGateway.findExpensesByUserCodeAndDate(userCode, date));
  }

  public SpendingResponse findExpense(String id) {
    return spendingResponseBuilder(spendingGateway.findExpenseById(id));
  }

  public Set<String> findCategories(String query) {
    return spendingGateway.findCategories(query);
  }

  public void saveSpent(SpendingRequest spendingRequest) {
    spendingGateway
        .findExpensesByUserCode(spendingRequest.getUserCode())
        .stream()
        .filter(
            spending ->
                spending.getDescription().equalsIgnoreCase(spendingRequest.getDescription()))
        .findFirst()
        .ifPresent(spending -> spendingRequest.setCategory(spending.getCategory()));
    spendingGateway.save(spendingBuilder(spendingRequest));
  }

  public void addCategory(SpendingRequest spendingRequest) {
    Spending spending = spendingGateway.findExpenseById(spendingRequest.getId());
    spending.setCategory(spendingRequest.getCategory());
    spendingGateway.save(spending);
  }

  private Spending spendingBuilder(SpendingRequest spendingRequest) {
    return Spending.builder()
        .id(spendingRequest.getId())
        .description(spendingRequest.getDescription())
        .category(spendingRequest.getCategory())
        .date(spendingRequest.getDate() != null ? spendingRequest.getDate().toLocalDate() : null)
        .time(spendingRequest.getDate() != null ? spendingRequest.getDate().toLocalTime() : null)
        .userCode(spendingRequest.getUserCode())
        .value(spendingRequest.getValue())
        .build();
  }

  private List<SpendingResponse> spendingListResponseBuilder(List<Spending> spending) {
    return spending
        .stream()
        .map(spent -> spendingResponseBuilder(spent))
        .collect(Collectors.toList());
  }

  private SpendingResponse spendingResponseBuilder(Spending spending) {
    return SpendingResponse.builder()
        .id(spending.getId())
        .description(spending.getDescription())
        .category(spending.getCategory())
        .userCode(spending.getUserCode())
        .date(spending.getDate() != null ? spending.getDate().atTime(spending.getTime()) : null)
        .value(spending.getValue())
        .build();
  }
}
