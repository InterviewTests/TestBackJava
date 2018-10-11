package br.com.santandertec.gestaodegastos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santandertec.gestaodegastos.dtos.GastoDTO;
import br.com.santandertec.gestaodegastos.models.Gasto;
import br.com.santandertec.gestaodegastos.models.GastoLista;
import br.com.santandertec.gestaodegastos.services.GastoService;

@RestController
@RequestMapping("/servico/gastos")
public class GastoRestController {
	
	@Autowired
	private GastoService gastoService;
	
	@GetMapping(value = "/cliente/{codigoCliente}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GastoLista> listarTodosOsGastosDoUsuarioLogado(@PathVariable Integer codigoCliente) {
		GastoLista gastosDoCliente = gastoService.listarGastos(codigoCliente);
		return new ResponseEntity<GastoLista>(gastosDoCliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Gasto> detalharGasto(@PathVariable Integer id) {
		Gasto gastoEncontrado = gastoService.buscarGasto(id);
		return new ResponseEntity<Gasto>(gastoEncontrado, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Gasto> cadastrarGasto(@Valid @RequestBody GastoDTO gastoDTO, BindingResult result) {
		
		if (result.hasErrors()) {
			StringBuilder erros = new StringBuilder();
			result.getAllErrors().forEach(error -> erros.append(error.getDefaultMessage()));
			throw new RuntimeException(erros.toString());
		}
		
		Gasto gastoCadastrado = gastoService.salvarGasto(gastoDTO);
		return new ResponseEntity<Gasto>(gastoCadastrado, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/categoria", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Gasto> cadastrarCategoriaDoGasto(@RequestBody Gasto gasto) {
		Gasto gastoAtualizado = gastoService.salvarCategoriaDoGasto(gasto);
		return new ResponseEntity<Gasto>(gastoAtualizado, HttpStatus.CREATED);
	}
	
}
