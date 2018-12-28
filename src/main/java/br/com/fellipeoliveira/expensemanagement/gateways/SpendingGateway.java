package br.com.fellipeoliveira.expensemanagement.gateways;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface SpendingGateway {

  List<Spending> findAllExpenses();

  List<Spending> findAllExpensesByDate(LocalDate date);

  Set<String> findCategories(String query);

  List<Spending> findExpensesByUserCode(Long userCode);

  Spending findExpenseById(String id);

  Spending save(Spending spending);
}
