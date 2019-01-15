package com.santander.test.backend.bweninger.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
public class GastoVO {

    private UUID idGasto;

    private String descricao;

    private BigDecimal valor;

    private LocalDateTime data;

    public UUID getIdGasto() {
        return idGasto;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public GastoVO(UUID idGasto, String descricao, BigDecimal valor, LocalDateTime data) {
        this.idGasto = idGasto;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
}
