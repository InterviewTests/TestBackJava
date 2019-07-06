package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;
import java.util.Optional;

public interface ClienteRepository extends SolrCrudRepository<Cliente, String> {
        Page<Cliente> findByNomeCliente(String nomeCliente, Pageable paginacao);
        Optional<Cliente> findByEmail(String email);
}