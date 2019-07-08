package io.santander.gastos.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.santander.gastos.service.DateUTCParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GastoVO {

    @Autowired
    private DateUTCParser dateParser;

    private String descricao;
    private Double valor;
    private Long codigoUsuario;
    private String data;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String classificacao;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long classificacaoId;
}
