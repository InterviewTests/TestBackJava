package br.com.testesantanderway.modelo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "cliente")
public class Cliente {
    @Id
    @Field
    private Long codigoUsuario;
    @Field
    private String nome;
    @Field
    private String email;
    @Field
    private String senha;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());

        return result;
    }

    public Long getCodigoUsuario() {
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
}