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

import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.services.UsuarioService;

@RestController
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuarios")
	public Page<UsuarioDTO> retrieveUsuarios(@RequestParam Map<String,String> allRequestParams) {
		return usuarioService.retrieveUsuarios(allRequestParams);
	}

	@GetMapping("/usuarios/{id}")
	public UsuarioDTO retrieveUsuario(@PathVariable long id) {
		return usuarioService.retrieveUsuario(id);
	}

	@DeleteMapping("/usuarios/{id}")
	public void deleteUsuario(@PathVariable long id) {
		usuarioService.deleteUsuario(id);
	}

	@PostMapping(path="/usuarios", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<Object> createUsuario(@RequestBody Usuario usuario) {
	public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuario) {
		UsuarioDTO savedUsuario = usuarioService.createUsuario(usuario);

//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedUsuario.getId()).toUri();
//		return ResponseEntity.created(location).build();

		return savedUsuario;
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Object> updateUsuario(@RequestBody UsuarioDTO usuario, @PathVariable long id) {

		usuario.setId(id);

		UsuarioDTO usuarioBanco = usuarioService.updateUsuario(usuario, id);

		if (usuarioBanco == null)
			return ResponseEntity.notFound().build();

		
		return ResponseEntity.noContent().build();
	}
}