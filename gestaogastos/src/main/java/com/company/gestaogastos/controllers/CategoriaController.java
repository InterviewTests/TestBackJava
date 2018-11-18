package com.company.gestaogastos.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.gestaogastos.domain.dto.CategoriaDTO;
import com.company.gestaogastos.services.CategoriaService;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/categorias")
	public Page<CategoriaDTO> retrieveCategorias(@RequestParam Map<String,String> allRequestParams) {
		return categoriaService.retrieveCategorias(allRequestParams);
	}

	@GetMapping("/categorias/{id}")
	public CategoriaDTO retrieveCategoria(@PathVariable long id) {
		return categoriaService.retrieveCategoria(id);
	}

	@DeleteMapping("/categorias/{id}")
	public void deleteCategoria(@PathVariable long id) {
		categoriaService.deleteCategoria(id);
	}

	@PostMapping(path="/categorias", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<Object> createCategoria(@RequestBody Categoria categoria) {
	public CategoriaDTO createCategoria(@RequestBody CategoriaDTO categoria) {
		CategoriaDTO savedCategoria = categoriaService.createCategoria(categoria);

//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedCategoria.getId()).toUri();
//		return ResponseEntity.created(location).build();

		return savedCategoria;
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<Object> updateCategoria(@RequestBody CategoriaDTO categoria, @PathVariable long id) {

		categoria.setId(id);

		CategoriaDTO categoriaBanco = categoriaService.updateCategoria(categoria, id);

		if (categoriaBanco == null)
			return ResponseEntity.notFound().build();

		
		return ResponseEntity.noContent().build();
	}
}