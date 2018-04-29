package com.santander.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.gastos.domain.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {

}
