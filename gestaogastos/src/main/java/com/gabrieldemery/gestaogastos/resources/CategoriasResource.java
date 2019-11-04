package com.gabrieldemery.gestaogastos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldemery.gestaogastos.entities.Categoria;
import com.gabrieldemery.gestaogastos.services.CategoriasService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriasResource {
	
	@Autowired
	CategoriasService categoriasService;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('USUARIO')")
	public List<Categoria> consultar(@RequestBody Categoria categoria) {
		return this.categoriasService.listar(categoria.getNome());
	}

}
