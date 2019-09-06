package br.com.santander.gastos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.gastos.service.CategoriaService;
import br.com.santander.gastos.vo.CategoriaVO;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/{descricao}")
	public ResponseEntity<List<CategoriaVO>> sugestaoCategoria(@PathVariable String descricao){
	
		List<CategoriaVO> listaCategorias = categoriaService.findByDescricao(descricao);
		
		return new ResponseEntity<>(listaCategorias, HttpStatus.OK);
	}
	
}
