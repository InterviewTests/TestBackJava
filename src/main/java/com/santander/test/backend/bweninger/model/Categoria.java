package com.santander.test.backend.bweninger.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
@Table("categorias")
public class Categoria {

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, name = "cpf_usuario", ordinal = 1)
    private String cpfUsuario;
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private String descricao;
    @Column
    private List<UUID> gastos;

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public List<UUID> getGastos() {
        return gastos;
    }

    public void setGastos(List<UUID> gastos) {
        this.gastos = gastos;
    }

    public void addGasto(UUID idGasto) {
        if (this.getGastos() == null) {
            this.gastos = new ArrayList<>();
        }
        this.gastos.add(idGasto);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
