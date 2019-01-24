package br.com.santander.spendingmanager.gateway.cassandra;

import br.com.santander.spendingmanager.domain.Spending;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpendingRepository extends CrudRepository<Spending, UUID> {

}
