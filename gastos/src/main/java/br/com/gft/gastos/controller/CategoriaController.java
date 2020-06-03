package br.com.gft.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.gastos.dto.CategoriaDTO;
import br.com.gft.gastos.model.Categoria;
import br.com.gft.gastos.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
	public Categoria novaCategoria(@RequestBody CategoriaDTO categoria) {
		return categoriaService.setCategoria(categoria);
	}
}
