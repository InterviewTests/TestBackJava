package com.company.gestaogastos.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Page<CategoriaDTO>> retrieveCategorias(@RequestParam Map<String,String> allRequestParams) {
		return new ResponseEntity<Page<CategoriaDTO>>(categoriaService.retrieveCategorias(allRequestParams), HttpStatus.OK);
	}

	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaDTO> retrieveCategoria(@PathVariable long id) {
		return new ResponseEntity<CategoriaDTO>(categoriaService.retrieveCategoria(id), HttpStatus.OK);
	}

	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Object> deleteCategoria(@PathVariable long id) {
		categoriaService.deleteCategoria(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(path="/categorias", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoria) {
		CategoriaDTO savedCategoria = categoriaService.createCategoria(categoria);
		return new ResponseEntity<CategoriaDTO>(savedCategoria, HttpStatus.OK);
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaDTO> updateCategoria(@RequestBody CategoriaDTO categoria, @PathVariable long id) {
		categoria.setId(id);
		CategoriaDTO savedCategoria = categoriaService.updateCategoria(categoria, id);
		if (savedCategoria == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<CategoriaDTO>(savedCategoria, HttpStatus.OK);
	}
}