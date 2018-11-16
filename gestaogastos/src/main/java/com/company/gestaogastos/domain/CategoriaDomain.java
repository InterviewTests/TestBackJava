package com.company.gestaogastos.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.entity.Gasto;
import com.company.gestaogastos.domain.repository.CategoriaRepository;

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

	public Page<Categoria> retrieveCategorias(Map<String, String> allRequestParams) {
		Page<Categoria> categorias;
		PageRequest pageRequest = getPageRequest(allRequestParams);
		if (allRequestParams.get("nome") != null) {
			String nomeParam = allRequestParams.get("nome");
			categorias = categoriaRepository.findByNome(nomeParam, pageRequest);
		} else {
			categorias = categoriaRepository.findAllCategoria(pageRequest);
		}
		return categorias;
	}
//	public Page<Categoria> retrieveCategorias(@PathVariable String nome) {
//		PageRequest pageRequest = PageRequest.of(0, CATEGORIAS_PAGE_SIZE, new Sort(Sort.Direction.ASC,"nome"));
//		Page<Categoria> categorias = categoriaRepository.findByNome(nome, pageRequest);
//		return categorias;
//	}

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

	public PageRequest getPageRequest(Map<String, String> allRequestParams) {
		Integer offset = 0;
		Integer limit = CATEGORIAS_PAGE_SIZE;
		if (allRequestParams.get("offset") != null) {
			offset = Integer.decode(allRequestParams.get("offset"));
		}
		if (allRequestParams.get("limit") != null) {
			limit = Integer.decode(allRequestParams.get("limit"));
		}
		return PageRequest.of(offset, limit, new Sort(Sort.Direction.ASC,"nome"));
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