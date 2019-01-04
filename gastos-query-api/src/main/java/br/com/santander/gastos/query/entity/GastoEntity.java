package br.com.santander.gastos.query.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos")
public class GastoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String descricao;

    @Column(nullable=false)
    private Double valor;

    @Column(name="codigo_usuario", nullable=false)
    private Long codigoUsuario;

    @Column(nullable=false)
    private LocalDateTime data;

    @Column
    private String categoria;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
