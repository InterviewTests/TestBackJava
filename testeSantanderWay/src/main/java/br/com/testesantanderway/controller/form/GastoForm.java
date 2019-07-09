package br.com.testesantanderway.controller.form;

import br.com.testesantanderway.modelo.Gasto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class GastoForm {
    @NotNull
    @NotEmpty
    private String descricao;
    @NotNull
    @NotEmpty
    private Double valor;
    @NotNull
    @NotEmpty
    private String codigoUsuario;
    @NotNull
    @NotEmpty
    private Date dataCriacao;

    public GastoForm() {
    }

    public GastoForm(Gasto gasto) {
        this.descricao = gasto.getDescricao();
        this.valor = gasto.getValor();
        this.dataCriacao = gasto.getDataCriacao();
        this.codigoUsuario = gasto.getCodigoUsuario();
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Gasto converter(String codigoSistema) {
        return new Gasto(descricao, valor, codigoSistema, codigoUsuario, dataCriacao);
    }
}