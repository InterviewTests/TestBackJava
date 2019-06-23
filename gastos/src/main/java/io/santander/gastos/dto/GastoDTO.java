package io.santander.gastos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoDTO {
    private Long id;
    private Long codigoUsuario;
    private String descricao;
    private Double valor;
    private Date data;
}
