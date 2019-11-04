package com.gabrieldemery.gestaogastos.resources;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldemery.gestaogastos.entities.Gasto;
import com.gabrieldemery.gestaogastos.entities.Usuario;
import com.gabrieldemery.gestaogastos.services.GastosService;
import com.gabrieldemery.gestaogastos.services.UsuariosService;

@RestController
@RequestMapping(value="/gastos")
public class GastosResource {
	
	@Autowired
	GastosService gastosService;
	
	@Autowired
	UsuariosService usuariosService;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('SISTEMA')")
	public void inserir(@RequestBody Gasto gasto) {
		this.gastosService.inserir(gasto);
	}

	@RequestMapping(value = "/lista", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('SISTEMA')")
	public void inserirLista(@RequestBody List<Gasto> gastos) {
		this.gastosService.inserirLista(gastos);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USUARIO')")
	public List<Gasto> listar(@RequestParam("data") Optional<String> data, Principal principal) {
		Usuario usuario = (Usuario) this.usuariosService.loadUserByUsername(principal.getName());
		return this.gastosService.listar(usuario, data);
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USUARIO')")
	public Gasto detalhar(@PathVariable(value = "codigo") Long codigo, Principal principal) {
		Usuario usuario = (Usuario) this.usuariosService.loadUserByUsername(principal.getName());
		return this.gastosService.detalhar(usuario, codigo);
	}

	@RequestMapping(value = "/{codigo}/categorizar", method = RequestMethod.PUT, consumes = "text/plain")
	@PreAuthorize("hasRole('USUARIO')")
	public void categorizar(@PathVariable(value = "codigo") Long codigo, @RequestBody String categoria) throws Exception {
		this.gastosService.categorizar(codigo, categoria);
	}
	
}
