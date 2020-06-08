package com.paulo.altran.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.altran.usuario.dto.UsuarioRetornoDTO;
import com.paulo.altran.usuario.model.Usuario;
import com.paulo.altran.usuario.service.UsuarioService;


@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;


	@PreAuthorize("#termo == authentication.name")
	@GetMapping("/pesquisa/acesso")
	public Long buscarPeloAcesso(@RequestParam String termo) {
		return this.usuarioService.buscarPorAcesso(termo);
	}
	
	@PreAuthorize("hasRole('ROLE_CONSULTAR_USUARIO')")
	@GetMapping("/{id}")
	public UsuarioRetornoDTO buscarPeloId(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.buscarPeloId(id);
		UsuarioRetornoDTO dto = UsuarioRetornoDTO.convertToUsuarioRetornoDTO(usuario);
		return dto;
	}
}
