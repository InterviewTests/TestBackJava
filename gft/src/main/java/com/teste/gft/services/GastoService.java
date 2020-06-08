package com.teste.gft.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.gft.entities.Categoria;
import com.teste.gft.entities.Gasto;
import com.teste.gft.entities.User;
import com.teste.gft.exceptions.ResourceNotFoundException;
import com.teste.gft.repositories.CategoriaRepository;
import com.teste.gft.repositories.GastoRepository;

@Service
public class GastoService {
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Gasto criarGasto(Gasto gasto) {
		Long id = gasto.getCodigoUsuario();
		String descricao = gasto.getDescricao();
		List<Gasto> lista = gastoRepository.findAllByCodigoUsuario(id);

		for (Gasto gastoLista : lista) {
			Categoria categoria = gastoLista.getCategoria();
			if (descricao.contentEquals(gastoLista.getDescricao()) && categoria != null) {
				gasto.setCategoria(categoria);
				break;
			}
		}

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
			List<Gasto> lista = gastoRepository.findAllByCodigoUsuarioAndData(id, data);
			if (lista == null) {
				throw new ResourceNotFoundException("Não existem gastos desse usuário");
			}
			return lista;
		}
		return null;

	}

	public Gasto atualizarCategoriaGasto(Categoria categoria, Long id, String email, String senha) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(senha);
		Long idUsuario = userService.autenticar(user);
		if (idUsuario != null) {
			return gastoRepository.findById(id).map(gasto -> {
				if (gasto.getCategoria() != null) {
					throw new ResourceNotFoundException("Esse gasto já tem uma categoria");
				}
				String nomeCategoria = categoria.getNome();
				Categoria categoriaExistente = categoriaRepository.findByNome(nomeCategoria);
				if (categoriaExistente == null) {
					categoriaExistente = categoriaRepository.save(categoria);
				}
				gasto.setCategoria(categoriaExistente);
				return gastoRepository.save(gasto);
			}).orElseThrow(() -> new ResourceNotFoundException("Gasto não existe id: " + id));

		}
		return null;

	}
}
