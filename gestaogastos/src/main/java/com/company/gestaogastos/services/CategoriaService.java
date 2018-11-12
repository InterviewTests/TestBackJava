package com.company.gestaogastos.services;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.gestaogastos.domain.entity.Categoria;


public interface CategoriaService {

	public List<Categoria> retrieveAllCategorias();

	public Categoria retrieveCategoria(@PathVariable long id);
	
	public Page<Categoria> retrieveCategoria2(@PathVariable String nome);

	public void deleteCategoria(@PathVariable long id);

	public Categoria createCategoria(@RequestBody Categoria categoria);
	
	public Categoria updateCategoria(@RequestBody Categoria categoria, @PathVariable long id);
}