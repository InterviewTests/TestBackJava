package io.santander.gastos.repository;

import io.santander.gastos.domain.CardSpent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSpentRepository extends JpaRepository<CardSpent, Long> {
}
