package br.com.brunots.testes.everis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunots.testes.everis.entities.GastoEntity;

@RestController
public class GastosController {
	
	@RequestMapping(value = "gastos", method = RequestMethod.POST)
	public ResponseEntity<Void> cadastrarGastos(List<GastoEntity> gastos) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
