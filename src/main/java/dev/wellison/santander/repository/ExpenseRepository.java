package dev.wellison.santander.repository;

import dev.wellison.santander.domain.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense,String> {
    List<Expense> findByUserCodeAndDate(Long userCode, Date date);

}
