package com.teste.gft.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.gft.entities.Gasto;
import com.teste.gft.entities.User;
import com.teste.gft.exceptions.ResourceNotFoundException;
import com.teste.gft.repositories.GastoRepository;

@Service
public class GastoService {
	@Autowired
	public GastoRepository gastoRepository;
	@Autowired
	public UserService userService;

	public Gasto criarGasto(Gasto gasto) {

		return gastoRepository.save(gasto);

	}

	public List<Gasto> listarGastosUsuario(Long id, String email, String senha) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(senha);
		Long idUsuario = userService.autenticar(user);
		if (idUsuario == id) {
			List<Gasto> lista = gastoRepository.findAllByCodigoUsuario(id);
			if (lista == null) {
				throw new ResourceNotFoundException("Não existem gastos desse usuário");
			}
			return lista;
		}
		return null;

	}

	public List<Gasto> listarGastosPorData(Long id, String email, String senha, LocalDate data) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(senha);
		Long idUsuario = userService.autenticar(user);
		if (idUsuario == id) {
			List<Gasto> lista = gastoRepository.findAllByCodigoUsuarioAndData(id,data);
			if (lista == null) {
				throw new ResourceNotFoundException("Não existem gastos desse usuário");
			}
			return lista;
		}
		return null;

	}
}
