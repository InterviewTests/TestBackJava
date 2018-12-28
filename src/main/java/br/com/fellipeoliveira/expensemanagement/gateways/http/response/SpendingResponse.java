package br.com.fellipeoliveira.expensemanagement.gateways.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpendingResponse {

  private String id;

  @JsonProperty("descricao")
  private String description;

  @JsonProperty("categoria")
  private String category;

  @JsonProperty("valor")
  private double value;

  @JsonProperty("codigousuario")
  private long userCode;

  @JsonProperty("data")
  private LocalDateTime date;
}
