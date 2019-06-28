package br.com.testesantanderway.modelo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.time.LocalDateTime;

@SolrDocument(collection = "cliente")
public class Cliente {

    @Id
    @Field
    private String codigoUsuario;
    @Field
    private String nome;
    @Field
    private String email;
    @Field
    private String senha;
    @Field
    private LocalDateTime dataCriacao;

    public Cliente() {
    }

    public Cliente(String nome, String email, String senha, LocalDateTime dataCriacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());

        return result;
    }
}