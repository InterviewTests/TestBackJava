package br.com.brunots.testes.everis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunots.testes.everis.entity.GastoEntity;
import br.com.brunots.testes.everis.service.GastosService;

@RestController
public class GastosController {

	@Autowired
	private GastosService service;

	@RequestMapping(value = "/gastos", method = RequestMethod.POST)
	public ResponseEntity<Void> cadastrarGastos(@RequestBody List<GastoEntity> gastos) {
		for (GastoEntity gastoEntity : gastos) {
			service.save(gastoEntity);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public ResponseEntity<List<GastoEntity>> listarGastos() {
		return new ResponseEntity<List<GastoEntity>>(service.listAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/gasto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> cadastrarGastos(@RequestBody GastoEntity gasto) {
		service.save(gasto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

}
