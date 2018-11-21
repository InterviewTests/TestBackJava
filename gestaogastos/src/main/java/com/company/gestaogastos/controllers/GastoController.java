package com.company.gestaogastos.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.company.gestaogastos.domain.dto.GastoDTO;
import com.company.gestaogastos.services.GastoService;

@RestController
public class GastoController {
	static final int GASTOS_PAGE_SIZE = 4;

	@Autowired
	GastoService gastoService;
	
	@GetMapping("/gastos")
	public ResponseEntity<Page<GastoDTO>> retrieveGastos(@RequestParam Map<String,String> allRequestParams) {
		return new ResponseEntity<Page<GastoDTO>>(gastoService.retrieveGastos(allRequestParams), HttpStatus.OK);
	}

	@GetMapping("/gastos/{id}")
	public ResponseEntity<GastoDTO> retrieveGasto(@PathVariable long id) {
		return new ResponseEntity<GastoDTO>(gastoService.retrieveGasto(id), HttpStatus.OK);
	}

	@DeleteMapping("/gastos/{id}")
	public ResponseEntity<String> deleteGasto(@PathVariable long id) {
		gastoService.deleteGasto(id);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}

	@PostMapping(path="/gastos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GastoDTO> createGasto(@RequestBody GastoDTO gasto) {
		if (gasto == null) {
			return new ResponseEntity<GastoDTO>(gasto, HttpStatus.BAD_REQUEST);
		}
		GastoDTO savedGasto = gastoService.createGasto(gasto);
		return new ResponseEntity<GastoDTO>(savedGasto, HttpStatus.OK);
	}
	
	@PutMapping(path="/gastos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GastoDTO> updateGasto(@RequestBody GastoDTO gasto) {
		GastoDTO savedGasto = gastoService.updateGasto(gasto, gasto.getId());
		return new ResponseEntity<GastoDTO>(savedGasto, HttpStatus.OK);
	}

}