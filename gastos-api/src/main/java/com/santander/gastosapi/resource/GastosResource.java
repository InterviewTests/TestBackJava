package com.santander.gastosapi.resource;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gastosapi.model.Gasto;
import com.santander.gastosapi.service.GastosService;

@RestController
@RequestMapping("/v1")
public class GastosResource {
	
	@Autowired
	private GastosService gastosService;
	
	@RequestMapping(path="/accredited/gastos/cartao", method=RequestMethod.POST)
	public void gastosPorCartao(@RequestBody Gasto gasto){
		gastosService.gastosPorCartao(gasto);
	}
	
	@RequestMapping(path="/protected/gastos/listagem", method=RequestMethod.GET)
	public @ResponseBody List<Gasto> listagemGastos(){
		return gastosService.listagemGastos();
	}
	
	@RequestMapping(path="/protected/gastos/listagem/filtro", method=RequestMethod.GET)
	public @ResponseBody List<Gasto> filtroGastos(@RequestHeader String filtro){
		return gastosService.filtroGastos(filtro);
	}
	
	@RequestMapping(path="/protected/gastos/categoria", method=RequestMethod.PUT)
	public void categorizacaoGastos(@RequestHeader String categoria, @RequestHeader String gastoID){
		gastosService.categorizacaoGastos(categoria, gastoID);
	}
	
	@RequestMapping(path="/protected/gastos/categoria/sugestao", method=RequestMethod.GET)
	public @ResponseBody LinkedHashSet<String> sugestaoCategoria(){
		return gastosService.sugestaoCategoria();
	}
}