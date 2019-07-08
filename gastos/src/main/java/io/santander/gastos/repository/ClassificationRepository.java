package io.santander.gastos.repository;

import io.santander.gastos.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    @Query(value = "SELECT c.name FROM Classification c WHERE c.name LIKE ?1 ")
    Set<String> findByText(String text);
}
