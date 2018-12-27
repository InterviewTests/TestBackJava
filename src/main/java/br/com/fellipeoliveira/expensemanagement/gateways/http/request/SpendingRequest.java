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

  @JsonProperty("descricao")
  private String description;

  @JsonProperty("valor")
  private double value;

  @JsonProperty("codigousuario")
  private int userCode;

  @JsonProperty("data")
  private LocalDateTime date; //formato UTC
}
