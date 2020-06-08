package com.paulo.altran.gasto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paulo.altran.gasto.dto.CategoriaCadastroDTO;
import com.paulo.altran.gasto.dto.CategoriaDTO;

@FeignClient("categorias")
public interface CategoriaClient {

	@GetMapping("/{categoriaId}")
	CategoriaDTO bucarpeloId(@PathVariable Long categoriaId);
	
	@PostMapping("")
	CategoriaDTO salvarCategoria(@RequestBody CategoriaCadastroDTO dto); 
	
}
