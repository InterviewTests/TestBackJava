package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Gasto;
import java.time.LocalDateTime;

public class DetalheGastosDTO {
    private String descricao;
    private Double valor;
    private LocalDateTime dataCriacao;

    public DetalheGastosDTO(Gasto gasto) {
        this.descricao = gasto.getDescricao();
        this.valor = gasto.getValor();
        this.dataCriacao = gasto.getDataCriacao();
    }

    public String getDescricao() { return descricao; }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
