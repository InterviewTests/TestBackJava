package br.com.rbs.testebackjava.repository;

import br.com.rbs.testebackjava.domain.Spending;
import org.springframework.data.repository.CrudRepository;

public interface SpendingRepository extends CrudRepository<Spending, Long> {
}
