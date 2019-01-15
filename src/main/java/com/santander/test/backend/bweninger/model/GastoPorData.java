package com.santander.test.backend.bweninger.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by BWeninger on 14/01/2019.
 */
@Table("gastos_por_data")
public class GastoPorData {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "cpf_usuario")
    private String cpfUsuario;

    @PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED)
    private LocalDateTime data;

    @Column("id_gasto")
    private UUID idGasto;

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public UUID getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(UUID idGasto) {
        this.idGasto = idGasto;
    }
}
