package com.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.model.Gasto;

public interface Gastos extends JpaRepository<Gasto, Integer> {

}
