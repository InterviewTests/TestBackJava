package br.com.gestao.gastos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao.gastos.model.Categorias;
import br.com.gestao.gastos.service.CategoriasService;

@RestController
@RequestMapping("/api")
public class CategoriasController {
	
	@Autowired
	private CategoriasService categoriasService;
	
	@RequestMapping(value = "/categorias/{categoria}", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Categorias>> getCategoria(@PathVariable String categoria){
		List<Categorias> categorias = categoriasService.listaCategoriasSugeridas(categoria);
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
}
