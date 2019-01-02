package br.com.fellipeoliveira.expensemanagement.gateways;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface SpendingGateway {

  Set<String> findCategories(String query);

  List<Spending> findExpensesByUserCode(Long userCode);

  List<Spending> findExpensesByUserCodeAndDate(Long userCode, LocalDate date);

  Spending findExpenseById(String id);

  Spending save(Spending spending);
}
