package br.com.fellipeoliveira.expensemanagement.gateways.impl;

import static br.com.fellipeoliveira.expensemanagement.domains.CacheUtils.SPENDING;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import br.com.fellipeoliveira.expensemanagement.gateways.SpendingGateway;
import br.com.fellipeoliveira.expensemanagement.gateways.repository.SpendingRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpendingGatewayImpl implements SpendingGateway {

  private final SpendingRepository spendingRepository;

  @Override
  public List<Spending> findAllExpenses() {
    return spendingRepository.findAll();
  }

  @Override
  public List<Spending> findAllExpensesByDate(LocalDate date) {
    return spendingRepository.findAllByDate(date);
  }

  @Override
  public Set<String> findCategories(String query) {
    return spendingRepository
        .findAllByCategoryContaining(query)
        .stream()
        .map(spending -> spending.getCategory())
        .collect(Collectors.toSet());
  }

  @Override
  public List<Spending> findExpensesByUserCode(Long userCode) {
    return spendingRepository.findAllByUserCode(userCode);
  }

  @Override
  public Spending findExpenseById(String id) {
    return spendingRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Gasto com ID " + id + " não foi encontrado!"));
  }

  @Override
  @CachePut(value = SPENDING, key = "#spending.id", unless = "#result == null")
  public Spending save(Spending spending) {
    return spendingRepository.save(spending);
  }
}
