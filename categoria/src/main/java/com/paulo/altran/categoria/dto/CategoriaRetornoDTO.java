package com.paulo.altran.categoria.dto;

import com.paulo.altran.categoria.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRetornoDTO {

	private Long id;
	private String nome;
	
	
	public static CategoriaRetornoDTO convertToCategoriaRetornoDTO(Categoria catedoria) {
		CategoriaRetornoDTO dto = CategoriaRetornoDTO.builder()
				.id(catedoria.getId())
				.nome(catedoria.getNome())
				.build();
		return dto;
						
	}
}
