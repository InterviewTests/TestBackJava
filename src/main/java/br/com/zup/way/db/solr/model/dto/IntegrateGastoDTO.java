package br.com.zup.way.db.solr.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegrateGastoDTO {

    @NotNull
    private Long codigoUsuario;

    @NotNull
    private String descricao;

    @NotNull
    private Double valor;

    private LocalDateTime dataCadastro;
}
