package io.santander.gastos.repository;

import io.santander.gastos.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(cc) > 0 THEN true ELSE false END FROM ClientCard cc " +
            "INNER JOIN cc.client c " +
            "INNER JOIN cc.creditCard ca " +
            "WHERE c.id = ?1 AND ca.id = ?2")
    boolean existsByClientAndCard(long client, long card);
}
