package br.com.zup.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe gasto
 */
public class Gasto implements Serializable {

    private String id;
    private String descricao;
    private BigDecimal valor;
    private Integer codigousuario;
    private Date data;
    private String Categoria;

    public Gasto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Integer codigousuario) {
        this.codigousuario = codigousuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}
