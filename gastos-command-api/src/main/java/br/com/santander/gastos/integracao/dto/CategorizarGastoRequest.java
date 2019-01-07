package br.com.santander.gastos.integracao.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategorizarGastoRequest implements Serializable {

    @NotEmpty
    private String categoria;

    private Long id;

    private Long codigoUsuario;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
