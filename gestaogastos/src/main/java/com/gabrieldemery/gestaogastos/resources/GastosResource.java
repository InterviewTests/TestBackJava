package com.gabrieldemery.gestaogastos.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldemery.gestaogastos.entities.Gasto;
import com.gabrieldemery.gestaogastos.services.GastosService;

@RestController
@RequestMapping(value="/gastos")
public class GastosResource {
	
	@Autowired
	GastosService gastosService;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	public void inserir(@RequestBody Gasto gasto) {
		this.gastosService.inserir(gasto);
	}

	@RequestMapping(value = "/lista", method = RequestMethod.POST, consumes = "application/json")
	public void inserirLista(@RequestBody List<Gasto> gastos) {
		this.gastosService.inserirLista(gastos);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Gasto> listar(@RequestParam("data") Optional<String> data) {
		return this.gastosService.listar(data);
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public Gasto detalhar(@PathVariable(value = "codigo") Long codigo) {
		return this.gastosService.detalhar(codigo);
	}

	@RequestMapping(value = "/{codigo}/categorizar", method = RequestMethod.PUT, consumes = "text/plain")
	public void categorizar(@PathVariable(value = "codigo") Long codigo, @RequestBody String categoria) throws Exception {
		this.gastosService.categorizar(codigo, categoria);
	}
	
}
