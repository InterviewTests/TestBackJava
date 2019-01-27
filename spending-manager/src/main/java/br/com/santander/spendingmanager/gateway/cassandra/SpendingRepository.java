package br.com.santander.spendingmanager.gateway.cassandra;

import br.com.santander.spendingmanager.domain.Spending;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SpendingRepository extends CrudRepository<Spending, UUID> {

    @AllowFiltering
    List<Spending> findSpendingsByDateIsGreaterThanAndDateIsLessThanAndUserCodeEquals(final LocalDateTime initialDate, final LocalDateTime finalDate, final int userCode);
}
