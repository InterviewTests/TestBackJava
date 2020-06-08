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
public class CategoriaCadastroDTO {

	private String nome;

	public Categoria convertToCategoria() {
		Categoria categoria = Categoria.builder().nome(this.nome).build();
		return categoria;
	}
}
