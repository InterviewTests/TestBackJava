package com.company.gestaogastos.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.entity.repository.CategoriaRepository;

public class CategoriaDomain {
	private Long id;
	private String nome;

	private CategoriaRepository categoriaRepository;
	
	static final int CATEGORIAS_PAGE_SIZE = 4;

	public CategoriaDomain() {
		super();
	}

	public CategoriaDomain(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public CategoriaDomain(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
	public List<Categoria> retrieveAllCategorias() {
		return categoriaRepository.findAll();
	}

	public Categoria retrieveCategoria(@PathVariable long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		return categoria.get();
	}

	public Page<Categoria> retrieveCategoria2(@PathVariable String nome) {
		Page<Categoria> categorias = categoriaRepository.findByNome(nome, new PageRequest(0, CATEGORIAS_PAGE_SIZE));
		return categorias;
	}

	public void deleteCategoria(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}

	public Categoria createCategoria(@RequestBody Categoria categoria) {
		Categoria savedCategoria = categoriaRepository.save(categoria);

		return savedCategoria;
	}
	
	public Categoria updateCategoria(@RequestBody Categoria categoria, @PathVariable long id) {

		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

		if (!categoriaOptional.isPresent())
			return null;

		categoria.setId(id);
		
		Categoria categoriaBanco = categoriaRepository.save(categoria);

		return categoriaBanco;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
    public String toString() {
        return String.format("Categoria{id=%s, nome=%s}"
        		, getId(), getNome());
    }

}