package br.com.santander.gastos.sugestaocategorias.repository;

import br.com.santander.gastos.sugestaocategorias.dto.CategoriaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CategoriasDocumentRepository extends SolrCrudRepository<CategoriaDocument, String> {

    @Query("id:*?0*")
    Page<CategoriaDocument> consultarCategorias(String id, Pageable pageable);
}