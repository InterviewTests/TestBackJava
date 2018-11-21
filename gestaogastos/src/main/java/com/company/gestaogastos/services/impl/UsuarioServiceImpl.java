package com.company.gestaogastos.services.impl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.gestaogastos.domain.UsuarioDomain;
import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.domain.repository.UsuarioRepository;
import com.company.gestaogastos.services.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Page<UsuarioDTO> retrieveUsuarios(Map<String, String> allRequestParams) {
		UsuarioDomain usuarioDomain = new UsuarioDomain(usuarioRepository);
		Page<UsuarioDTO> usuarios = usuarioDomain.retrieveUsuarios(allRequestParams);
		return usuarios;
	}

	@Override
	public UsuarioDTO retrieveUsuario(long id) {
		UsuarioDomain usuario = new UsuarioDomain(usuarioRepository);
		usuario.setId(id);
		UsuarioDTO usuarioDTO = usuario.retrieveUsuario();
		return usuarioDTO;
	}

	@Override
	public void deleteUsuario(long id) {
		UsuarioDomain usuario = new UsuarioDomain(usuarioRepository);
		usuario.setId(id);
		usuario.deleteUsuario();
	}

	@Override
	public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
		UsuarioDomain usuario = new UsuarioDomain(usuarioRepository);
		usuario.toDomain(usuarioDTO);
		UsuarioDTO usuarioBanco = usuario.createUsuario();
		return usuarioBanco;
	}
	
	@Override
	public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, long id) {
		UsuarioDomain usuario = new UsuarioDomain(usuarioRepository);
		usuario.toDomain(usuarioDTO);
		usuario.setId(id);
		UsuarioDTO usuarioBanco = usuario.updateUsuario();
		return usuarioBanco;
	}

}