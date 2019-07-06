package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Gasto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;

public interface GastoRepository extends SolrCrudRepository<Gasto, String>{
        Page<Gasto> findByCategoria(String categoria, Pageable paginacao);
        Optional<Gasto> findByDataCriacao(String data);
        Optional<String> findCategoriaByDescricao(String descricao);
}