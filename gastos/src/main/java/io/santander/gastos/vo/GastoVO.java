package io.santander.gastos.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.santander.gastos.commons.DateParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GastoVO {

    @Autowired
    private DateParser dateParser;

    private String descricao;
    private Double valor;
    private Long codigoUsuario;
    private Date data;
}
