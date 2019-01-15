package com.santander.test.backend.bweninger.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
@Table("gastos")
public class Gasto {

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    private UUID id;
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    private LocalDateTime data;
    @PrimaryKeyColumn(name = "cpf_usuario", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private String cpfUsuario;
    @Column
    private String categoria;
    @Column
    private String descricao;
    @Column
    private BigDecimal valor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
