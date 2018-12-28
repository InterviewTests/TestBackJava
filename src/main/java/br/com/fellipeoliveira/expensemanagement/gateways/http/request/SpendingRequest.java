package br.com.fellipeoliveira.expensemanagement.gateways.http.request;

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
public class SpendingRequest {

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
  private LocalDateTime date; //formato UTC
}
