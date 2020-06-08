package com.paulo.altran.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.altran.usuario.exception.ResourceNotFoundException;
import com.paulo.altran.usuario.model.Usuario;
import com.paulo.altran.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarPeloId(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo id: " + id));
		return usuario;
	}

	public Long buscarPorAcesso(String acesso) {
		Usuario usuario = this.usuarioRepository.findByAcesso(acesso)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo acesso: " + acesso));
		return usuario.getId();
	}

}
