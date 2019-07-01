package io.santander.gastos.repository;

import io.santander.gastos.domain.Spent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpentRepository extends JpaRepository<Spent, Long> {

    @Query(value = "SELECT s FROM CardSpent cs " +
            "INNER JOIN cs.creditCard cc " +
            "INNER JOIN cs.spent s " +
            "WHERE cc.id IN ?1 " +
            "AND (?2 IS NULL OR s.description = ?2) " +
            "AND (?3 IS NULL OR s.spentValue = ?3) " +
            "AND (?4 IS NULL OR s.spentDate = ?4) ")
    Page<Spent> findAllWithFilters(List<Long> cards, String description, Double value, Date date, Pageable pageable);
}
