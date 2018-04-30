package br.com.santander.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value = "/cadastro/incluirGasto", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluirGasto(@RequestBody Gasto gasto) throws Exception {
		try {
			gastosService.save(gasto);
			return new ResponseEntity<String>("Pedido Cadastrado com sucesso!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value= "/busca/listarGastos/{codigoUsuario}" , method= RequestMethod.GET) 
	public ResponseEntity<Iterable<Gasto>> listarGastosUsuario(@PathVariable Long codigoUsuario) {
		return new ResponseEntity< Iterable<Gasto> >(gastosService.findByCodigoUsuario(codigoUsuario),HttpStatus.OK); 
	}
	
	@RequestMapping(value= "/listarTodos" , method= RequestMethod.GET) 
	public ResponseEntity<Iterable<Gasto>> listarTodos() {
		return new ResponseEntity< Iterable<Gasto> >(gastosService.listarTodos(),HttpStatus.OK); 
	}
	
	@RequestMapping(value="/busca/gastosPorDataGasto/{codigoUsuario},{dataGasto}", method = RequestMethod.GET) 
	public ResponseEntity <List<Gasto>> buscaGastosPorDataGasto(
			@PathVariable Long codigoUsuario, 
			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate dataGasto){
		return new ResponseEntity <List<Gasto>>(gastosService.findByDataGasto(codigoUsuario, dataGasto), HttpStatus.OK);
	}
	
	@RequestMapping(value="/busca/intervaloData/{codigoUsuario},{dataInicio},{dataFim}", method = RequestMethod.GET) 
	public ResponseEntity <List<Gasto>> buscaGastosPorIntervaloData(
			@PathVariable Long codigoUsuario, 
			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate dataInicio,
			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate dataFim){
		return new ResponseEntity <List<Gasto>>(gastosService.findByDataGasto(codigoUsuario, dataInicio, dataFim), HttpStatus.OK);
	}
	
//	@RequestMapping(value="/busca/gastosRecentes/{idCliente, dataInicio, dataFim}", method = RequestMethod.GET) 
//	public ResponseEntity <List<Gasto>> buscaGastosUltimosCincoSegundosPorIdCliente(
//			@PathVariable Long idCliente, 
//			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDateTime dataInicio, 
//			@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDateTime dataFim) {
//		return new ResponseEntity <List<Gasto>>(gastosService.pesquisarGastosUltimosCincoSegundosPorIdCliente(idCliente, dataInicio, dataFim), HttpStatus.OK);
//	}
	
}
