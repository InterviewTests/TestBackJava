package br.com.testesantanderway.dto;

import br.com.testesantanderway.modelo.Gasto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GastoDTO {
    private LocalDateTime dataCriacao;
    private String descricao;
    private Double valor;
    private String categoria;

    public GastoDTO(Gasto gasto) {
        this.dataCriacao = gasto.getDataCriacao();
        this.descricao = gasto.getDescricao();
        this.valor = gasto.getValor();
        this.categoria = gasto.getCategoria();
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static List<GastoDTO> converter(List<Gasto> gastos) {
        return gastos.stream().map(GastoDTO::new).collect(Collectors.toList());
    }

    public static Page<GastoDTO> converter(Page<Gasto> gastos) {
        return gastos.map(GastoDTO::new);
    }

}