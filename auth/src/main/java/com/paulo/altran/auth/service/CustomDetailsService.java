package com.paulo.altran.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paulo.altran.auth.model.Usuario;
import com.paulo.altran.auth.repository.UsuarioRepository;

@Service
public class CustomDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String acesso) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByAcesso(acesso)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválidos!"));
		UserDetails user = new User(usuario.getAcesso(), usuario.getSenha(), usuario.getAuthorities());
		return user;
	}
}