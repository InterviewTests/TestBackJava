package com.santander.gestaogastos.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gestaogastos.model.Categoria;
import com.santander.gestaogastos.repository.CategoriaRepositorio;
import com.santander.gestaogastos.service.CategoriaServico;

@Service
public class CategoriaServicoImpl implements CategoriaServico <Categoria> {
	
	@Autowired
	private CategoriaRepositorio categoriaReposiorio;
	
	@Override
	public Categoria salvarCategoria(Categoria categoriaIn) {
		Categoria categoria = new Categoria(categoriaReposiorio);
		
		return categoria.salvarCategoria(categoriaIn);		
	}

	@Override
	public <E> List<Categoria> listaCategoria() {
		Categoria categoria = new Categoria(categoriaReposiorio);
		
		return categoria.listaCategoria();
	}

	@Override
	public <E> Object pesquisarCategoria(Integer id) {
		Categoria categoria = new Categoria(categoriaReposiorio);
		
		return categoria.pesquisarCategoria(id);
	}

	@Override
	public void removeCategoria(Categoria categoriaIn) {
		Categoria categoria = new Categoria(categoriaReposiorio);
		
		categoria.removeCategoria(categoriaIn);	
	}

}
