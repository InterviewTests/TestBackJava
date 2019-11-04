package com.gabrieldemery.gestaogastos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabrieldemery.gestaogastos.entities.Usuario;
import com.gabrieldemery.gestaogastos.repositories.UsuariosRepository;

@Service
public class UsuariosService implements UserDetailsService {

	@Autowired
	private UsuariosRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = this.usuarioRepository.findByUsuario(username);
		
		if (usuario == null)
			throw new UsernameNotFoundException("Usuario não encontrado.");
		
		return usuario;
	}

}
