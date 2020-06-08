package com.paulo.altran.gasto.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String acesso;
	private List<String> roles;	
	
}
