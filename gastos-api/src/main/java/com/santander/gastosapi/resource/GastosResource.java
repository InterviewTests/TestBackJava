package com.santander.gastosapi.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gastosapi.model.Categoria;
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
	public @ResponseBody List<Gasto> filtroGastos(@RequestParam Date filtro){
		return gastosService.filtroGastos(filtro);
	}
	
	@RequestMapping(path="/protected/gastos/categoria", method=RequestMethod.POST)
	public void categorizacaoGastos(@RequestBody Categoria categoria){
		gastosService.categorizacaoGastos(categoria);
	}
	
	@RequestMapping(path="/protected/gastos/categoria/sugestao", method=RequestMethod.GET)
	public @ResponseBody List<Categoria> sugestaoCategoria(){
		return gastosService.sugestaoCategoria();
	}
	
	@RequestMapping(path="/protected/gastos/automatica", method=RequestMethod.GET)
	public void categorizacaoAutomaticaGastos(){
		
	}
}