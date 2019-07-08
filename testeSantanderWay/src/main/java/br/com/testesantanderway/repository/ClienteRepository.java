package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Cliente;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;


public interface ClienteRepository extends SolrCrudRepository<Cliente, String> {
    Optional<Cliente> findByEmail(String email);
}
