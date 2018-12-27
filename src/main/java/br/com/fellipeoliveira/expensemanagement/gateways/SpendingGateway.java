package br.com.fellipeoliveira.expensemanagement.gateways;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import java.util.List;

public interface SpendingGateway {

  List<Spending> findAllSpending();

  Spending save(Spending spending);
}
