package br.com.zup.way.db.solr.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroGastoDTO {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime filtroData;
}
