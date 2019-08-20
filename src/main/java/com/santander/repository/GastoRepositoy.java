package com.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.model.Gasto;

@Repository
public interface GastoRepositoy extends JpaRepository<Gasto, Long> {

}
