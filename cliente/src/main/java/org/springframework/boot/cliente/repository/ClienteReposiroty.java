package org.springframework.boot.cliente.repository;

import org.springframework.boot.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteReposiroty extends JpaRepository<Cliente, Long> {
}
