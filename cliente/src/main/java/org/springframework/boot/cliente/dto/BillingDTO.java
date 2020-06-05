package org.springframework.boot.cliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.cliente.model.Billing;
import org.springframework.boot.cliente.model.Cliente;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BillingDTO  implements Serializable {

    private Long codigousuario;
    private String descricao;
    private Integer valor;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date data;

    public Billing billingDtoToBilling(){
        Billing billing = new Billing();
        billing.setCodigousuario(this.codigousuario);
        billing.setDescricao(this.descricao);
        billing.setValor(this.valor);
        billing.setData(this.data);
        return  billing;
    }
}
