package br.com.santander.spendingmanager.gateway.http.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpendingResponseJson {

    @JsonProperty("spendings")
    private List<SpendingItemResponseJson> items;
}
