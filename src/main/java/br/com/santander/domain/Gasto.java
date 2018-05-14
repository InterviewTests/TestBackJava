package br.com.santander.domain;

import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table
public class Gasto {
    private UUID id;
    private String descricao;
    private Double valor;
    private long codigousuario;
    private Date data;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public long getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(long codigousuario) {
        this.codigousuario = codigousuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
