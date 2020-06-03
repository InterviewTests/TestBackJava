package br.com.gft.gastos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.gastos.dto.CategoriaDTO;
import br.com.gft.gastos.model.Categoria;
import br.com.gft.gastos.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria setCategoria(CategoriaDTO categoria) {
		Categoria novaCategoria = new Categoria();
		novaCategoria.setCategoria(categoria.getCategoria());
		
		categoriaRepository.save(novaCategoria);
		return novaCategoria;
	}
}	
