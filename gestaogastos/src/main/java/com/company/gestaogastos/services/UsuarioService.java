package com.company.gestaogastos.services;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.gestaogastos.domain.dto.UsuarioDTO;

public interface UsuarioService {

	public Page<UsuarioDTO> retrieveUsuarios(Map<String, String> allRequestParams);

	public UsuarioDTO retrieveUsuario(long id);
	
	public void deleteUsuario(long id);

	public UsuarioDTO createUsuario(UsuarioDTO usuario);
	
	public UsuarioDTO updateUsuario(UsuarioDTO usuario, long id);
}