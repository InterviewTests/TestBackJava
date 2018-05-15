package br.com.santander.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gasto {

    @Id
    private String id;
    @NotNull(message = "Deve ser fornecida uma descrição para o gasto.")
    private String descricao;
    @NotNull(message = "O valor do gasto deve ser informado")
    private Double valor;
    @NotNull(message = "É necessário informar o código do usuário")
    private long codigoUsuario;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
