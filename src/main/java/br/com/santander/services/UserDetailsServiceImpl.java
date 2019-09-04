package br.com.santander.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.santander.entities.Usuario;
import br.com.santander.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.usuarioPorUsername(username);
		
		System.out.println("DADOS-------------------------------");
		System.out.println(usuario.getUsername());
		System.out.println();
		return new UserSecurity(usuario.getUuid(), usuario.getUsername(), usuario.getPassword(), usuario.getPerfis(), usuario.getCodigo());
	}
	
	public UserSecurity get(){
        return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
