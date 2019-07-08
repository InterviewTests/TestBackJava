package br.com.testesantanderway.modelo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Date;

@SolrDocument(collection = "gastos")
public class Gasto {

    @Id
    @Field
    private String codigo;
    @Field
    private String descricao;
    @Field
    private String categoria;
    @Field
    private Double valor;
    @Field
    private Date dataCriacao;
    @Field
    private String codigoSistema;
    @Field
    private String codigoUsuario;

    public Gasto() {
    }

    public Gasto(String categoria) {
        this.categoria = categoria;
    }

    public Gasto(String descricao, Double valor, String codigoSistema, String codigoUsuario, Date dataCriacao) {
        this.descricao = descricao;
        this.valor = valor;
        this.codigoSistema = codigoSistema;
        this.codigoUsuario = codigoUsuario;
        this.dataCriacao = dataCriacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCodigoSistema() { return codigoSistema; }

    public void setCodigoSistema(String codigoSistema) { this.codigoSistema = codigoSistema; }

    public String getCodigoUsuario() { return codigoUsuario; }

    public void setCodigoUsuario(String codigoUsuario) { this.codigoUsuario = codigoUsuario; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());

        return result;
    }

}
