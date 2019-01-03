package br.com.santander.gastos.sugestaocategorias.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.ChildDocument;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@SolrDocument(solrCoreName = "categorias")
public class CategoriasCliente {

    @Id
    private Long id;

    @ChildDocument
    @Indexed
    private List<CategoriaDocument> categorias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CategoriaDocument> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDocument> categorias) {
        this.categorias = categorias;
    }
}