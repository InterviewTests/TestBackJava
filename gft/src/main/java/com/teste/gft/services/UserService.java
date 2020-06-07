package com.teste.gft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.gft.entities.User;
import com.teste.gft.exceptions.ResourceNotFoundException;
import com.teste.gft.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	public UserRepository userRepository;

	public Long autenticar(User user) {
		String senha = user.getPassword();
		String usuario = user.getUsername();

		User newUser = userRepository.findByUsernameAndPassword(usuario, senha);

		if (newUser == null) {
			throw new ResourceNotFoundException("Usuario não autenticado");
		}
		return newUser.getId();

	}

	public User criarUsuario(User user) {
		return userRepository.save(user);
	}
}
