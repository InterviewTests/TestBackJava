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
	public List<Categorias> listaCategoriasSugeridas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorias findCategeoria(String categoria) {
		return categoriasRepository.findByNomeCategoria(categoria);
	}

	@Override
	public Categorias save(Categorias gastos) {
		categoriasRepository.save(gastos);
		return null;
	}

}
