package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ClienteRepository extends SolrCrudRepository <Cliente, String> {
    Page<Cliente> findByNome(String nome, Pageable paginacao);
}