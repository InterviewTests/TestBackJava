package br.com.gestao.gastos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public List<Gastos> getAllPets() {
	  return gastosService.listAll();
	}
}
