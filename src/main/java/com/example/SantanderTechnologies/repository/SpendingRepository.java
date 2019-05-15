package com.example.SantanderTechnologies.repository;

import com.example.SantanderTechnologies.model.Spending;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    Optional<Spending> findById(Long spendingId);

    @EntityGraph(value = "CreditCard.users", type = EntityGraph.EntityGraphType.FETCH)
    List<Spending> findAllByCreditCardId(Long creditCardId);
}
