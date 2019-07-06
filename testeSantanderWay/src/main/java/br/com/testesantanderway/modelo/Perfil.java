package br.com.testesantanderway.modelo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.security.core.GrantedAuthority;

@SolrDocument(collection = "perfil")
public class Perfil implements GrantedAuthority {
    @Id
    @Field
    private String codigoUsuario;

    @Field
    private String nomePerfil;

    public Perfil(String codigoUsuario, String nomePerfil) {
        this.codigoUsuario = codigoUsuario;
        this.nomePerfil = nomePerfil;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNome() {
        return nomePerfil;
    }

    public void setNome(String nome) {
        this.nomePerfil = nome;
    }

    @Override
    public String getAuthority() {
        return nomePerfil;
    }
}