package com.example.SantanderTechnologies.repository;

import com.example.SantanderTechnologies.model.CreditCard;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findById(Long creditCardId);

    @EntityGraph(value = "CreditCard.users", type = EntityGraph.EntityGraphType.FETCH)
    List<CreditCard> findAllByUserId(Long userId);
}
