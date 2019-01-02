package br.com.santander.gastos.integracao.repository;

import br.com.santander.gastos.integracao.entity.GastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GastoRepository extends JpaRepository<GastoEntity,Long>, QuerydslPredicateExecutor<GastoEntity> {
}
