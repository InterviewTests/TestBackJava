package br.com.santander.gastos.sugestaocategorias.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@SolrDocument(solrCoreName = "categorias")
public class CategoriaDocument {

    @Id
    private String id;

    @Indexed
    private List<String> descricoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDescricoes() {
        return descricoes;
    }

    public void setDescricoes(List<String> descricoes) {
        this.descricoes = descricoes;
    }
}
