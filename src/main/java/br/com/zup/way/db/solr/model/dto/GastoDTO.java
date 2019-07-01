package br.com.zup.way.db.solr.model.dto;

import br.com.zup.way.db.solr.model.Gasto;
import br.com.zup.way.util.mapper.GastoMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class GastoDTO {

    private String id;

    @NotNull
    private String descricao;

    @NotNull
    private Double valor;

    @NotNull
    private Long codigoUsuario;

    private LocalDateTime dataCadastro;

    private String categoria;

    public static GastoDTO convert(Gasto gasto) {
        return GastoMapper.INSTANCE.gastoToGastoDTO(gasto);
    }

    public static List<GastoDTO> convert(List<Gasto> gasto) {
        return GastoMapper.INSTANCE.gastoToGastoDTO(gasto);
    }

}
