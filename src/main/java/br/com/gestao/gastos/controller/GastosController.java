package br.com.gestao.gastos.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao.gastos.model.Gastos;
import br.com.gestao.gastos.service.GastosService;

@RestController
@RequestMapping("/api")
public class GastosController {

	@Autowired
	private GastosService gastosService;
	
	@RequestMapping(value = "/gastos/{codigousuario}", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Gastos>> getListaDeGastos(@PathVariable int codigousuario, @RequestParam(value = "data", required=false) String data) {
		List<Gastos> gastos = gastosService.listaDeGastos(codigousuario, data);
		return new ResponseEntity<>(gastos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gastos/{codigousuario}/{id}", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<Gastos> getDetalheGasto(@PathVariable int codigousuario, @PathVariable ObjectId id) {
		Gastos gastos = gastosService.detalheGasto(codigousuario, id);
		return new ResponseEntity<>(gastos, HttpStatus.OK);
	}

	@RequestMapping(value = "/gastos", method = RequestMethod.POST)
	public ResponseEntity<Gastos> criarGastos(@Valid @RequestBody Gastos gastos) {
		gastosService.criarGasto(gastos);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/gastos", method = RequestMethod.PUT)
	public ResponseEntity<Gastos> alterarGasto(@Valid @RequestBody Gastos gastos) {
		gastosService.alterarGasto(gastos);
		return new ResponseEntity<>(gastos, HttpStatus.NO_CONTENT);
	}
}
