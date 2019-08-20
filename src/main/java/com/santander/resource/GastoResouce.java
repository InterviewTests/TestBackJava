package com.santander.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santander.model.Gasto;
import com.santander.service.GastoService;

@RestController
@RequestMapping("/")
public class GastoResouce {

	@Autowired
	private GastoService gastoService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gasto> criarGasto(@Valid @RequestBody Gasto gasto) {

		Gasto gastoSalvo = gastoService.salvar(gasto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/usuario/{codigoUsuario}")
				.buildAndExpand(gastoSalvo.getCodigoUsuario()).toUri();

		return ResponseEntity.created(uri).body(gastoSalvo);
	}
}