package io.santander.gastos.repository;

import io.santander.gastos.domain.Spent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpentRepository extends JpaRepository<Spent, Long> {

    @Query(value = "SELECT spent FROM card_spent AS cs INNER JOIN spent ON cs.spent = spent.id INNER JOIN card ON cs.card = card.id INNER JOIN client_card AS cc ON cc.card = card.id INNER JOIN client ON client.id = cc.client;",nativeQuery = true)
    Page<Spent> findAllWithFilters(Long userCode, String cardNumber, String description, Double value, String date, Pageable pageable);
}
