package com.santander.gestaogastos.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santander.gestaogastos.exception.GastosException;
import com.santander.gestaogastos.model.Categoria;
import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.model.Response;
import com.santander.gestaogastos.service.CategoriaServicoImpl;
import com.santander.gestaogastos.service.GastosServicoImpl;

@Controller
@RequestMapping("/gestaogastos")
public class GastoController {
	
	private static final Logger logger = LoggerFactory.getLogger(GastoController.class);
	
	@Autowired
	private CategoriaServicoImpl categoriaService;
	@Autowired
	private GastosServicoImpl gastosService;
	
	@RequestMapping(value="/gastos", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Gasto>> listarGastos() {
		
		logger.info("Retornando todos os Gastos");
		
		List<Gasto> gastosDoCliente = gastosService.listaGastos();
		
 		if (!gastosDoCliente.isEmpty()) {
 	       return ResponseEntity.ok().body(gastosDoCliente);
 	    }else{
 	    	return ResponseEntity.notFound().build();
 	    }		
	}
	
	@RequestMapping(value = "/gastos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeGastoPorId(@PathVariable("id") Integer id) throws GastosException {
		logger.info("Gasto id para ser excluido " + id);

		Gasto gasto = (Gasto) this.gastosService.pesquisarGasto(id);

		if (gasto != null) {
			throw new GastosException("Gasto para ser excluido não existe !");
		}

		gastosService.removeGasto(gasto);

		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Gasto foi excluido"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gastos/{id}", method = RequestMethod.GET)
 	public @ResponseBody ResponseEntity<Gasto> detalhar(@PathVariable("id") Integer id) throws GastosException {
		
		logger.info("Gasto id para ser retornado " + id);
 		
		Gasto gasto = (Gasto) this.gastosService.pesquisarGasto(id);
	 		
 		if (gasto!=null) {
   	       return ResponseEntity.ok().body(gasto);
   	    }else{
   	    	throw new GastosException("Gasto não existe");
   	    }
 	}
	
	@RequestMapping(value = "/gastos/{data}", method = RequestMethod.GET)
 	public @ResponseBody ResponseEntity<List<Gasto>> listarGastosPorData( @PathVariable("data") String data) throws ParseException {
 		List<Gasto> gastosDoClientePorData = this.gastosService.gastosDoClientePorData(data);
 		
 		if (!gastosDoClientePorData.isEmpty()) {
  	       return ResponseEntity.ok().body(gastosDoClientePorData);
  	    }else{
  	    	return ResponseEntity.notFound().build();
  	    }
 	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/gastos", method = RequestMethod.PATCH)
	public ResponseEntity<Response> updateGastoComCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {

		Gasto gasto = (Gasto) this.gastosService.pesquisarGasto(id);

		if (gasto != null) {
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Gasto Não encontrado com este ID"), HttpStatus.OK);
		}else {
			
			gasto.setCategoria(categoria);
			
			this.gastosService.salvarGasto(gasto);
		}

		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Gasto foi alterado"), HttpStatus.OK);
	}
	
	@PostMapping(value = "/gastos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Gasto> cadastrarGasto(@RequestBody Gasto gasto) {
		Gasto gastoCriado = this.gastosService.salvarGasto(gasto);
 		
 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gastoCriado.getId()).toUri();

 		return ResponseEntity.created(location).build();
	}
	
	@PostMapping(value = "/categoria", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 	public ResponseEntity<Categoria> cadastrarCategoriaDoGasto(@RequestBody Categoria categoria) {
 		Categoria categoriaCriada = this.categoriaService.salvarCategoria(categoria);
 		return new ResponseEntity<Categoria>(categoriaCriada, HttpStatus.CREATED);
 	}
	
	

}
