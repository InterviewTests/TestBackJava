package com.paulo.altran.gasto.dto;

import java.time.LocalDateTime;

import com.paulo.altran.gasto.model.Gasto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GastoRetornoDTO {

	private Long codigogasto;
	private String descricao;
	private Double valor;
	private Long codigousuario;
	private LocalDateTime data;	
	private String categoria;
	
	
	public static GastoRetornoDTO convertToGastoRestornoDTO(Gasto gasto) {
		String categoria = gasto.getCategoria() != null ? gasto.getCategoria() : null; 
		GastoRetornoDTO dto = GastoRetornoDTO.builder()
								.codigogasto(gasto.getId())
								.descricao(gasto.getDescricao())
								.valor(gasto.getValor())
								.codigousuario(gasto.getCodigoUsuario())
								.data(gasto.getData())
								.categoria(categoria)
								.build();
		return dto;
	}
}
