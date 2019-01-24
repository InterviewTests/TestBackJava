package br.com.santander.spendingmanager.gateway.http.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpendingRequestJson {

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("valor")
    private double value;

    @JsonProperty("codigousuario")
    private int userCode;

    @JsonProperty("data")
    private LocalDate date;
}
