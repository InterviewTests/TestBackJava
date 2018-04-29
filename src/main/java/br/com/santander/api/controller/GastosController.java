package br.com.santander.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.api.model.Gasto;
import br.com.santander.api.service.GastosService;

@RequestMapping("/gastos")
@RestController
public class GastosController {

	@Autowired
	private GastosService gastosService;
	
	@RequestMapping(value = "/cadastro/incluirGasto", method = RequestMethod.POST)
	public ResponseEntity<String> salvar(@RequestBody Gasto gasto){
		
		gastosService.save(gasto);
		
		return new ResponseEntity<String>("Gasto inserido com sucesso", HttpStatus.CREATED);
	}

	@RequestMapping(value= "/busca/listarGastos/{codigoUsuario}" , method= RequestMethod.GET) 
	public ResponseEntity<Iterable<Gasto>> listarGastosUsuario(@PathVariable Long codigoUsuario) {
		return new ResponseEntity< Iterable<Gasto> >(gastosService.findByCodigoUsuario(codigoUsuario),HttpStatus.OK); 
	}
	
	@RequestMapping(value= "/listarTodos" , method= RequestMethod.GET) 
	public ResponseEntity<Iterable<Gasto>> listarTodos() {
		return new ResponseEntity< Iterable<Gasto> >(gastosService.listarTodos(),HttpStatus.OK); 
	}
	
//	@RequestMapping(value="/busca/gastosPorData/{idCliente, data}", method = RequestMethod.GET) 
//	public ResponseEntity <List<Gasto>> buscaGastosPorData(
//			@PathVariable Long idCliente, 
//			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDateTime data){
//		return new ResponseEntity <List<Gasto>>(gastosService.pesquisarGastosPorData(idCliente, data), HttpStatus.OK);
//	}
	
//	@RequestMapping(value="/busca/gastosRecentes/{idCliente, dataInicio, dataFim}", method = RequestMethod.GET) 
//	public ResponseEntity <List<Gasto>> buscaGastosUltimosCincoSegundosPorIdCliente(
//			@PathVariable Long idCliente, 
//			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDateTime dataInicio, 
//			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDateTime dataFim) {
//		return new ResponseEntity <List<Gasto>>(gastosService.pesquisarGastosUltimosCincoSegundosPorIdCliente(idCliente, dataInicio, dataFim), HttpStatus.OK);
//	}
	
}
