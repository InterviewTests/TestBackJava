package com.santander.gestaogastos.controller;

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

import com.santander.gestaogastos.exception.GastosException;
import com.santander.gestaogastos.model.Categoria;
import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.model.Response;
import com.santander.gestaogastos.serviceImpl.CategoriaServicoImpl;
import com.santander.gestaogastos.serviceImpl.GastosServicoImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API PARA CONTROLE DE GASTOS")
@Controller
@RequestMapping("/gestaogastos")
public class GastoController {
	
	private static final Logger logger = LoggerFactory.getLogger(GastoController.class);
	
	@Autowired
	private CategoriaServicoImpl categoriaService;
	@Autowired
	private GastosServicoImpl gastosService;
	
	@ApiOperation(value = "Retorna a lista de todos os gastos")
	@RequestMapping(value="/gastos", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Gasto>> listarGastos() {
		
		logger.info("Retornando todos os Gastos");
		
 	    return ResponseEntity.ok().body(gastosService.listaGastos());
	}
	
	@ApiOperation(value = "Remoçao do gasto")
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
	
	@ApiOperation(value = "Pesquisa do gasto")
	@RequestMapping(value = "/gastos/{id}", method = RequestMethod.GET)
 	public @ResponseBody ResponseEntity<Gasto> detalhar(@PathVariable("id") Integer id) throws GastosException {
		
		logger.info("Gasto id para ser retornado " + id);
 		
   	    return ResponseEntity.ok().body((Gasto) this.gastosService.pesquisarGasto(id));
 	}
	
	@ApiOperation(value = "Pesquisa do gasto através da data")
	@RequestMapping(value = "/gastos/{data}", method = RequestMethod.GET)
 	public @ResponseBody ResponseEntity<List<Gasto>> listarGastosPorData( @PathVariable("data") String data) throws ParseException {

       return ResponseEntity.ok().body(this.gastosService.gastosDoClientePorData(data));
 	}

	@ApiOperation(value = "Atualiza do gasto")
	@SuppressWarnings("null")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
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
	
	@ApiOperation(value = "Cadastro do gasto")
	@PostMapping(value = "/gastos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Gasto> cadastrarGasto(@RequestBody Gasto gasto) {
 		return new ResponseEntity<Gasto>(this.gastosService.salvarGasto(gasto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Cadastro da categoria")
	@PostMapping(value = "/categoria", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 	public ResponseEntity<Categoria> cadastrarCategoriaDoGasto(@RequestBody Categoria categoria) {
 		return new ResponseEntity<Categoria>(this.categoriaService.salvarCategoria(categoria), HttpStatus.CREATED);
 	}
	
	

}
