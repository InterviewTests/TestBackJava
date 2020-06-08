package com.paulo.altran.usuario.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.paulo.altran.usuario.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRetornoDTO {

	private Long id;
	private String nome;
	private String acesso;
	private List<String> roles;
	
	public static UsuarioRetornoDTO convertToUsuarioRetornoDTO(Usuario usuario) {
		UsuarioRetornoDTO dto = UsuarioRetornoDTO.builder()
				.id(usuario.getId())
				.nome(usuario.getNome())
				.acesso(usuario.getAcesso())
				.roles(usuario.getRoles().stream().map(role -> role.getNome()).collect(Collectors.toList()))
				.build();
		return dto;
	}
	
}
