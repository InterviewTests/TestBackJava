package br.com.gft.gastos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.gastos.dto.GastoDTO;
import br.com.gft.gastos.model.Gasto;
import br.com.gft.gastos.service.GastoService;


@RestController
@RequestMapping("/gastos")
public class GastoController{
	
	@Autowired
	private GastoService gastoService;
	
	@PostMapping
	public Gasto novoGasto(@RequestBody GastoDTO gasto){
		return gastoService.setGasto(gasto);
	}
	
	@GetMapping("/listar")
	public List<Gasto> listaGastos(){
		return gastoService.lista();
	}
	
	@GetMapping("/listarPorCliente/{id}")
	public List<Gasto> listarGastosCliente(@PathVariable Long id){
		return gastoService.listaPorIdCliente(id);
	}
	
	@GetMapping("/listarPorCliente/{id}/{data}")
	public List<Gasto> listarGastosCliente(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date data){
		return gastoService.listaPorIdClienteEData(id, data);
	}
	
}
