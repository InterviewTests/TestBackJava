package br.com.rbs.testebackjava.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Spending {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "CODIGO_USUARIO")
    private Integer codigousuario;

    @Column(name = "DATA ")
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
