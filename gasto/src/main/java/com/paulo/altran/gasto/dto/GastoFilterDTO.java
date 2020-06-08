package com.paulo.altran.gasto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GastoFilterDTO {

	private String descricao;
	private Double valor;
	private String categoria;
}
