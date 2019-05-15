package com.example.SantanderTechnologies.repository;

import com.example.SantanderTechnologies.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    Optional<Spending> findById(Long spendingId);

    List<Spending> findAllByCreditCardId(Long creditCardId);
}
