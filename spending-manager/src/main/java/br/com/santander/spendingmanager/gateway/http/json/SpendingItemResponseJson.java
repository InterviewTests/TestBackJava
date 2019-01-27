package br.com.santander.spendingmanager.gateway.http.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpendingItemResponseJson {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("valor")
    private Double value;

    @JsonProperty("codigousuario")
    private int userCode;

    @JsonProperty("data")
    private LocalDateTime date;

    @JsonProperty("categoria")
    private String category;
}
