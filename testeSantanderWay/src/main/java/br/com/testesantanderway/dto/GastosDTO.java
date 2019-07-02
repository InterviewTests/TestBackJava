package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Gasto;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

public class GastosDTO {
    private LocalDateTime dataCriacao;
    private String descricao;
    private Double valor;

    public GastosDTO(Gasto gasto) {
        this.dataCriacao = gasto.getDataCriacao();
        this.descricao = gasto.getDescricao();
        this.valor = gasto.getValor();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    public static Page<GastosDTO> converter(Page<Gasto> gastos) {
        return gastos.map(GastosDTO::new);
    }
}