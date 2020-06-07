package com.teste.gft.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.gft.entities.Gasto;
import com.teste.gft.services.GastoService;

@RestController
public class GastoController {
	@Autowired
	GastoService gastoServices;

	@PostMapping("/gasto")
	public ResponseEntity<Gasto> createGasto(@RequestBody Gasto gasto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(gasto);
		}
		gastoServices.criarGasto(gasto);
		return ResponseEntity.ok(gasto);
	}

	@GetMapping("/gasto/{id}")
	public ResponseEntity<List<Gasto>> listById(@PathVariable Long id, @RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		List<Gasto> lista = gastoServices.listarGastosUsuario(id, username, password);
		if (lista == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(lista);

	}

	@GetMapping("/gastos/{id}")
	public ResponseEntity<List<Gasto>> listByIdAndData(@PathVariable Long id,
			@RequestHeader("username") String username, @RequestHeader("password") String password,
			@RequestParam("data") String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataPesquisa = LocalDate.parse(data, formatter);

		List<Gasto> lista = gastoServices.listarGastosPorData(id, username, password, dataPesquisa);
		if (lista == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(lista);

	}
}
