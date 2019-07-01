package br.com.zup.way.db.solr.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroCategorizarGastoDTO {

    @NotNull
    private String categoria;

    @NotNull
    private String idGasto;
}
