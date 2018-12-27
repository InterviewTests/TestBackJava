package br.com.fellipeoliveira.expensemanagement.gateways.impl;

import static br.com.fellipeoliveira.expensemanagement.domains.CacheUtils.SPENDING;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import br.com.fellipeoliveira.expensemanagement.gateways.SpendingGateway;
import br.com.fellipeoliveira.expensemanagement.gateways.repository.SpendingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpendingGatewayImpl implements SpendingGateway {

  private final SpendingRepository spendingRepository;


  @Override
  public List<Spending> findAllSpending() {
    return spendingRepository.findAll();
  }

  @Override
  @CachePut(value = SPENDING, key = "#spending.id", unless = "#result == null")
  public Spending save(Spending spending) {
    return spendingRepository.save(spending);
  }
}
