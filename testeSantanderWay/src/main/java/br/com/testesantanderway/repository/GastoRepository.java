package br.com.testesantanderway.repository;

import br.com.testesantanderway.modelo.Gasto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GastoRepository extends SolrCrudRepository<Gasto, String>{
        List<Gasto> findByCodigoUsuarioAndDataCriacaoAfter(String codigoUsuario, LocalDateTime dataCriacao);

        Page<Gasto> findByDataCriacao(String data, Pageable paginacao);

        Optional<String> findCategoriaByDescricao(String descricao);
}