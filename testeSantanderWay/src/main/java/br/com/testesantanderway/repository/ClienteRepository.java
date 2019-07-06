package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Sistema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;
import java.util.Optional;

public interface ClienteRepository extends SolrCrudRepository<Sistema, String> {
        Page<Sistema> findByNomeCliente(String nomeCliente, Pageable paginacao);
        Optional<Sistema> findByEmail(String email);
}