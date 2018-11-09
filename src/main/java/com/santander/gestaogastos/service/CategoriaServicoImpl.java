package com.santander.gestaogastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gestaogastos.model.Categoria;
import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.repository.CategoriaRepositorio;

@Service
public class CategoriaServicoImpl implements CategoriaServico <Categoria> {
	
	@Autowired
	private CategoriaRepositorio categoriaDAO;
	
	@Override
	public Categoria salvarCategoria(Categoria categoriaIn) {
		return this.categoriaDAO.save(categoriaIn);		
	}

	@Override
	public <E> List<Categoria> listaCategoria() {
		List<Categoria> categorias = categoriaDAO.findAll();
		return categorias;
	}

	@Override
	public <E> Object pesquisarCategoria(Integer id) {
		return this.categoriaDAO.getOne(id);
	}

	@Override
	public void removeCategoria(Categoria categoriaIn) {
		this.categoriaDAO.delete(categoriaIn);	
		
	}

}
