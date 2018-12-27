package br.com.fellipeoliveira.expensemanagement.gateways.repository;

import br.com.fellipeoliveira.expensemanagement.domains.Spending;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpendingRepository extends MongoRepository<Spending, String> {

}
