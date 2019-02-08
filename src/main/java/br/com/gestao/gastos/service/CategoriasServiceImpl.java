package br.com.gestao.gastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao.gastos.model.Categorias;
import br.com.gestao.gastos.repository.CategoriasRepository;

@Service
public class CategoriasServiceImpl implements CategoriasService {

	private CategoriasRepository categoriasRepository;

	@Autowired
	public CategoriasServiceImpl(CategoriasRepository categoriasRepository) {
		this.categoriasRepository = categoriasRepository;
	}

	@Override
	public List<Categorias> listaCategoriasSugeridas(String categoria) {
		List<Categorias> categorias = categoriasRepository.findCategoriasSugeridas(".*"+categoria+".*");
		return categorias;
	}

}
