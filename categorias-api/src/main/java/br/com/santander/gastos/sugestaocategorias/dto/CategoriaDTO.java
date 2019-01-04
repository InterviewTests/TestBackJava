package br.com.santander.gastos.sugestaocategorias.dto;

import java.io.Serializable;
import java.util.List;

public class CategoriaDTO implements Serializable {

    private String id;

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
