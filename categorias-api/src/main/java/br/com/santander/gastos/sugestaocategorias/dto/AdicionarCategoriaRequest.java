package br.com.santander.gastos.sugestaocategorias.dto;

import java.io.Serializable;

public class AdicionarCategoriaRequest implements Serializable {

    private Long idCliente;

    private String categoria;

    private String descricao;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
