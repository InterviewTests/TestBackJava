package com.santander.interview.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Expense {
    @Id
    private String id;
    private String descricao;
    private double valor;
    private long codigoUsuario;
    private Date data;

    public Expense() { }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }

    public void setValor(double valor) { this.valor = valor; }

    public long getCodigoUsuario() { return codigoUsuario; }

    public void setCodigoUsuario(long codigoUsuario) { this.codigoUsuario = codigoUsuario; }

    public Date getData() { return data; }

    public void setData(Date data) { this.data = data; }

    @Override
    public String toString() {
        return String.format(
                "Expense[id=%s, descricao=%s, valor=%f, codigoUsuario=%d, data=%s]",
                this.id, this.descricao, this.valor, this.codigoUsuario, this.data.toString()
        );
    }
}
