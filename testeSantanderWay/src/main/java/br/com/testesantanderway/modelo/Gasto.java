package br.com.testesantanderway.modelo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;
import java.time.LocalDateTime;

@SolrDocument(collection = "gastos")
public class Gasto {

    @Id
    @Field
    private String codigoGasto;
    @Field
    private String descricao;
    @Field
    private String categoria;
    @Field
    private Double valor;
    @Field
    private LocalDateTime dataCriacao;

    public Gasto() {
    }

    public Gasto(String categoria) {
        this.categoria = categoria;
    }

    public Gasto(String descricao, Double valor , LocalDateTime dataCriacao) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }

    public String getCodigoGasto() {
        return codigoGasto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCodigoGasto(String codigoGasto) {
        this.codigoGasto = codigoGasto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((codigoGasto == null) ? 0 : codigoGasto.hashCode());

        return result;
    }
}
