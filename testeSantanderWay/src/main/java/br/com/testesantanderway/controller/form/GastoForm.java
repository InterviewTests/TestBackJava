package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Gasto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GastoForm {
    @NotNull
    @NotEmpty
    private String descricao;
    @NotNull
    @NotEmpty
    private Double valor;
    private LocalDateTime dataCriacao;

    public GastoForm() {
    }

    public GastoForm(Gasto gasto) {
        this.descricao = gasto.getDescricao();
        this.valor = gasto.getValor();
        this.dataCriacao = gasto.getDataCriacao();
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Gasto converter() {
        return new Gasto(descricao, valor, dataCriacao);
    }
}