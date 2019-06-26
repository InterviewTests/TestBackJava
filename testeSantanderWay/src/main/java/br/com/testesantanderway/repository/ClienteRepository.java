package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Cliente;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ClienteRepository extends SolrCrudRepository <Cliente, Long> {
    Cliente findByNome(String nome);
}