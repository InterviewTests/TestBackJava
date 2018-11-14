package com.company.gestaogastos.controllers;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.gestaogastos.domain.entity.Gasto;
import com.company.gestaogastos.services.GastoService;

@RestController
public class GastoController {
	static final int GASTOS_PAGE_SIZE = 4;

	@Autowired
	GastoService gastoService;
	
//	@GetMapping("/gastos")
//	public List<Gasto> retrieveAllGastos() {
//		return gastoService.retrieveAllGastos();
//	}
	@GetMapping("/gastos")
	public Page<Gasto> retrieveAllGastos(@RequestParam Map<String,String> allRequestParams) {
		return gastoService.retrieveGastos(allRequestParams);
	}

	@GetMapping("/gastos/{id}")
	public Gasto retrieveGasto(@PathVariable long id) {
		return gastoService.retrieveGasto(id);
	}

//	@GetMapping("/gastos/users/{id}")
//	public Page<Gasto> retrieveGastoByUser(@PathVariable Integer id, @RequestParam Map<String,String> allRequestParams) {
//		Page<Gasto> gastos = gastoService.retrieveGastoByUser(id);
//		return gastos;
//	}
//	@GetMapping("/gastos/users/{id}")
//	public Page<Gasto> retrieveGastoByUser(@PathVariable Integer id) {
//		Page<Gasto> gastos = gastoService.retrieveGastoByUser(id);
//		return gastos;
//	}
//
//	@GetMapping("/gastos/users/{id}/{date}")
//	public Page<Gasto> retrieveGastoByUserDate(@PathVariable("id") Integer id, @PathVariable("date") String date) {
//		return gastoService.retrieveGastoByUserDate(id, date);
//	}

	@DeleteMapping("/gastos/{id}")
	public void deleteGasto(@PathVariable long id) {
		gastoService.deleteGasto(id);
	}

	@PostMapping(path="/gastos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> createGasto(@RequestBody Gasto gasto) {
	public Gasto createGasto(@RequestBody Gasto gasto) {
		if (gasto == null) {
			// return ResponseEntity.notFound().build();
			return null;
		}
		Gasto savedGasto = gastoService.createGasto(gasto);

//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedGasto.getId()).toUri();
//		return ResponseEntity.created(location).build();
		return savedGasto;
	}
	
//	@PutMapping(path="/gastos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> updateGasto(@RequestBody Gasto gasto, @PathVariable long id) {
//		gastoService.updateGasto(gasto, id);
//		return ResponseEntity.noContent().build();
//	}

	@PutMapping(path="/gastos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Gasto updateGasto(@RequestBody Gasto gasto) {
		Gasto savedGasto = gastoService.updateGasto(gasto, gasto.getId());
		
		return savedGasto;
	}

}