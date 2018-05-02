package com.santander.gastos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gastos.domain.Categoria;
import com.santander.gastos.services.CategoriaService;

@RestController
@RequestMapping(value="/api/v1/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		
		List<Categoria> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj) ;
	}
	
	@RequestMapping(value="/nome/{categoria}",method=RequestMethod.GET)
	public ResponseEntity<?> buscarPorNome(@PathVariable String categoria) {
		
		List<Categoria> obj = service.buscarPorNome(categoria).orElse(null);
		
		return ResponseEntity.ok().body(obj) ;
	}
	
}
	
