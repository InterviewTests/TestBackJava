package br.com.rbs.testebackjava.repository;

import br.com.rbs.testebackjava.domain.Spending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    Page<Spending> findByCodigoUsuario(Long codigoUsuario, Pageable pageRequest);
}
