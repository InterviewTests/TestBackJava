package br.com.adslima.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.adslima.model.ExpenseManagement;

@Transactional(readOnly = true)
public interface ExpenseManagementRepository extends JpaRepository<ExpenseManagement, String> {

	List<ExpenseManagement> findExpensesCardsByUserCode(Integer userCode);

	Page<ExpenseManagement> findExpensesCardsByUserCode(Integer userCode, Pageable pageable);

	Page<ExpenseManagement> findByUserCodeAndDateBefore(Integer userCode, LocalDateTime date, Pageable pageable);

	Page<ExpenseManagement> findByUserCodeAndDateBetween(Integer userCode, LocalDateTime initDate,
			LocalDateTime endDate, Pageable pageable);

	List<ExpenseManagement> findByDescription(final String description);

}
