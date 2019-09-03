package com.santander.interview.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Expense {
    @Id
    private String id;
    private String descricao;
    private double valor;
    private int codigousuario;
    private Date data;

    public Expense() { }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }

    public void setValor(double valor) { this.valor = valor; }

    public int getCodigousuario() { return codigousuario; }

    public void setCodigousuario(int codigousuario) { this.codigousuario = codigousuario; }

    public Date getData() { return data; }

    public void setData(Date data) { this.data = data; }
}
