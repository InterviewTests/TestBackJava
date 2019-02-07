package br.com.gestao.gastos.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao.gastos.model.Gastos;
import br.com.gestao.gastos.service.GastosService;

@RestController
@RequestMapping("/api")
public class GastosController {

	@Autowired
	private GastosService gastosService;
	
	@RequestMapping(value = "/gastos/{codigousuario}", produces="application/json", method = RequestMethod.GET)
	public List<Gastos> getListaDeGastos(@PathVariable int codigousuario) {
		List<Gastos> gastos = gastosService.listaDeGastos(codigousuario);
		return gastos;
	}

	@RequestMapping(value = "/gastos", method = RequestMethod.POST)
	public Gastos criarGastos(@Valid @RequestBody Gastos gastos) {
		gastosService.save(gastos);
		return null;
	}
}
