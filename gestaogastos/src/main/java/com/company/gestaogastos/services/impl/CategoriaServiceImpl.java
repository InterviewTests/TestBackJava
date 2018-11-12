package com.company.gestaogastos.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.gestaogastos.domain.CategoriaDomain;
import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.entity.repository.CategoriaRepository;
import com.company.gestaogastos.services.CategoriaService;


@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> retrieveAllCategorias() {
		return categoriaRepository.findAll();
	}

	public Categoria retrieveCategoria(@PathVariable long id) {
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Categoria categoria = categoriaDomain.retrieveCategoria(id);
		return categoria;
	}

	public Page<Categoria> retrieveCategoria2(@PathVariable String nome) {
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Page<Categoria> categorias = categoriaDomain.retrieveCategoria2(nome);
		return categorias;
	}

	public void deleteCategoria(@PathVariable long id) {
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		categoriaDomain.deleteCategoria(id);
	}

	public Categoria createCategoria(@RequestBody Categoria categoria) {
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Categoria categoriaBanco = categoriaDomain.createCategoria(categoria);
		return categoriaBanco;
	}
	
	public Categoria updateCategoria(@RequestBody Categoria categoria, @PathVariable long id) {
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Categoria categoriaBanco = categoriaDomain.updateCategoria(categoria, id);
		return categoriaBanco;
	}
}