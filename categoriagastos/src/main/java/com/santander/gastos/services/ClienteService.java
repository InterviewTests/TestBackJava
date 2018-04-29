package com.santander.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.santander.gastos.security.UserSS;
import com.santander.gastos.services.UserService;
import com.santander.gastos.services.exceptions.AuthorizationException;
import com.santander.gastos.services.exceptions.ObjectNotFoundException;
import com.santander.gastos.domain.Cliente;
import com.santander.gastos.domain.enuns.Perfil;
import com.santander.gastos.dto.ClienteDTO;
import com.santander.gastos.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getTelefone(), objDto.getDocumento(),
				pe.encode(objDto.getSenha()));
	}

	public Cliente buscarPorId(Integer id) {

		Optional<Cliente> cli = repo.findById(id);

		return cli.orElse(null);
	}

	public List<Cliente> findAll() {

		return repo.findAll();

	}
	
	public Cliente findByEmail(String email) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Cliente obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

}
