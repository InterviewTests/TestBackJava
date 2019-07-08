package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Sistema;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;

public interface SistemaRepository extends SolrCrudRepository<Sistema, String> {
        @Query("email:?")
        Optional<Sistema> findByEmail(String email);
}