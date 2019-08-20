package com.santander.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.model.Gasto;
import com.santander.repository.gasto.GastoRepositoyQuery;

@Repository
public interface GastoRepositoy extends JpaRepository<Gasto, Long>, GastoRepositoyQuery {

	Optional<Gasto> findByDescricao(String descricao);

}
