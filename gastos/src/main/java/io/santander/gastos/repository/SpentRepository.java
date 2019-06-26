package io.santander.gastos.repository;

import io.santander.gastos.domain.Spent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SpentRepository extends JpaRepository<Spent, Long> {

    @Query(value = "SELECT s FROM Spent s " +
            "WHERE s.userCode = ?1 " +
            "AND s.cardNumber = ?2 " +
            "AND (?3 is NULL OR s.description = ?3) " +
            "AND (?4 is NULL OR s.spentValue = ?4) " +
            "AND (?5 is NULL OR s.spentDate = ?5) ")
    Page<Spent> findAllWithFilters(Long userCode, String cardNumber, String description, Double value, Date date, Pageable pageable);
}
