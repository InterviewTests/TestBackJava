package com.teste.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.gft.entities.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

}
