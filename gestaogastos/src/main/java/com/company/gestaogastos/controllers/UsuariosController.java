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

import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.services.UsuarioService;

@RestController
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuarios")
	public ResponseEntity<Page<UsuarioDTO>> retrieveUsuarios(@RequestParam Map<String,String> allRequestParams) {
		return new ResponseEntity<Page<UsuarioDTO>>(usuarioService.retrieveUsuarios(allRequestParams), HttpStatus.OK);
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> retrieveUsuario(@PathVariable long id) {
		return new ResponseEntity<UsuarioDTO>(usuarioService.retrieveUsuario(id), HttpStatus.OK);
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Object>  deleteUsuario(@PathVariable long id) {
		usuarioService.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(path="/usuarios", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuario) {
		UsuarioDTO savedUsuario = usuarioService.createUsuario(usuario);
		return new ResponseEntity<UsuarioDTO>(savedUsuario, HttpStatus.OK);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO>  updateUsuario(@RequestBody UsuarioDTO usuario, @PathVariable long id) {
		usuario.setId(id);
		UsuarioDTO savedUsuario = usuarioService.updateUsuario(usuario, id);
		if (savedUsuario == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<UsuarioDTO>(savedUsuario, HttpStatus.OK);
	}
}