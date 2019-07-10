package io.santander.gastos.repository;

import io.santander.gastos.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    @Query(value = "FROM Classification c WHERE c.name LIKE ?1 ")
    List<Classification> findByText(String text);

    @Query(value = "SELECT cla FROM CardSpent cs " +
            "INNER JOIN cs.creditCard cc " +
            "INNER JOIN cs.spent s " +
            "INNER JOIN s.classification cla " +
            "WHERE cc.id IN ?1 " +
            "AND s.description = ?2 ")
    Classification findSpendClassification(List<Long> cards, String description);
}
