package com.paulo.altran.categoria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paulo.altran.categoria.exception.ResourceNotFoundException;
import com.paulo.altran.categoria.model.Categoria;
import com.paulo.altran.categoria.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria salvarCategoria(Categoria categoria) {
		Optional<Categoria> categoriaExistente = categoriaRepository.findByNome(categoria.getNome());
		Categoria categoriaSalva;
		if (!categoriaExistente.isPresent()) {
			categoriaSalva = this.categoriaRepository.save(categoria);
		} else {
			categoriaSalva = categoriaExistente.get();
		}
		return categoriaSalva;
	}

	public Categoria buscarPeloId(Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada pelo id: " + id));
		return categoria;
	}

	public Page<Categoria> buscarCategoria(String nome, Pageable pageable) {
		Categoria categoria = Categoria.builder().nome(nome).build();
		Example<Categoria> example = Example.of(categoria, ExampleMatcher.matchingAny().withIgnoreCase()
				.withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING));
		Page<Categoria> categorias = categoriaRepository.findAll(example, pageable);
		return categorias;
	}

	public Categoria buscarCategoriaPeloNomeExato(String nome) {
		Categoria categoria = categoriaRepository.findByNome(nome)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada pelo nome: " + nome));
		return categoria;
	}

}
