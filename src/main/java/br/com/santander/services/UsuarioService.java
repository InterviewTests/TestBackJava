package br.com.santander.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.entities.Usuario;
import br.com.santander.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario usuarioPorUsername(String username) {
		Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
		if(!usuario.isPresent()) {
			ResponseUtil.mensagemErro("Não foi encontrado nenhum usuário com o username "+username);
		}
		return usuario.get();
	}
}
