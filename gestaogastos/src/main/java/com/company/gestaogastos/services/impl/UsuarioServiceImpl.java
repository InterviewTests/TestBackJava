package com.company.gestaogastos.services.impl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.gestaogastos.domain.UsuarioDomain;
import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.domain.entity.Usuario;
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
		Usuario usuarioDTO = usuario.retrieveUsuario();
		return usuario.toUsuarioDTO(usuarioDTO);
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
		Usuario entity = usuario.toEntity(usuarioDTO);
		Usuario usuarioBanco = usuario.createUsuario(entity);
		return usuario.toUsuarioDTO(usuarioBanco);
	}
	
	@Override
	public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, long id) {
		UsuarioDomain usuario = new UsuarioDomain(usuarioRepository);
		Usuario entity = usuario.toEntity(usuarioDTO);
		entity.setId(id);
		Usuario usuarioBanco = usuario.updateUsuario(entity);
		return usuario.toUsuarioDTO(usuarioBanco);
	}

}