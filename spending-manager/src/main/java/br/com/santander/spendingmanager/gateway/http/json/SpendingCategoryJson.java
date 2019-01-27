package br.com.santander.spendingmanager.gateway.http.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpendingCategoryJson {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("categoria")
    private String category;
}
