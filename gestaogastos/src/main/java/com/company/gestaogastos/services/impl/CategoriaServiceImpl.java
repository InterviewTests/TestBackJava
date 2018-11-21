package com.company.gestaogastos.services.impl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.gestaogastos.domain.Categoria;
import com.company.gestaogastos.domain.dto.CategoriaDTO;
import com.company.gestaogastos.domain.repository.CategoriaRepository;
import com.company.gestaogastos.services.CategoriaService;


@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Page<CategoriaDTO> retrieveCategorias(Map<String, String> allRequestParams) {
		Categoria categoriaDomain = new Categoria(categoriaRepository);
		Page<com.company.gestaogastos.domain.entity.Categoria> categorias = categoriaDomain.retrieveCategorias(allRequestParams);
		return categoriaDomain.convertPageCategoriaToPageCategoriaDTO(categorias);
	}

	@Override
	public CategoriaDTO retrieveCategoria(long id) {
		Categoria categoria = new Categoria(categoriaRepository);
		categoria.setId(id);
		com.company.gestaogastos.domain.entity.Categoria categoriaReturn = categoria.retrieveCategoria();
		return categoria.toDTO(categoriaReturn);
	}

	@Override
	public void deleteCategoria(long id) {
		Categoria categoria = new Categoria(categoriaRepository);
		categoria.setId(id);
		categoria.deleteCategoria();
	}

	@Override
	public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria(categoriaRepository);
		com.company.gestaogastos.domain.entity.Categoria entity = categoria.toEntity(categoriaDTO);
		com.company.gestaogastos.domain.entity.Categoria categoriaBanco = categoria.createCategoria(entity);
		return categoria.toDTO(categoriaBanco);
	}
	
	@Override
	public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO, long id) {
		Categoria categoria = new Categoria(categoriaRepository);
		com.company.gestaogastos.domain.entity.Categoria entity = categoria.toEntity(categoriaDTO);
		categoria.setId(id);
		com.company.gestaogastos.domain.entity.Categoria categoriaBanco = categoria.updateCategoria(entity);
		return categoria.toDTO(categoriaBanco);
	}

}