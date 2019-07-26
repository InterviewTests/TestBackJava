package santander.api.domain.repository;

import org.springframework.data.repository.CrudRepository;
import santander.api.domain.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense,String> {
    List<Expense> findByUserCodeAndDate(Long userCode, Date date);

}
