package br.com.fellipeoliveira.expensemanagement.gateways.repository;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpendingRepository extends MongoRepository<Spending, String> {

  List<Spending> findAllByDate(LocalDate date);

  List<Spending> findAllByUserCode(Long userCode);

  List<Spending> findAllByCategoryContaining(String query);
}
