package br.com.brunots.testes.everis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunots.testes.everis.entity.GastoEntity;
import br.com.brunots.testes.everis.facade.AuthenticationFacade;
import br.com.brunots.testes.everis.service.GastosService;

@RestController
@RequestMapping(path = "/gastos")
public class GastosController {

	@Autowired
	private GastosService service;
	@Autowired
	private AuthenticationFacade auth;
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> cadastrarGastos(@RequestBody List<GastoEntity> gastos) {
		for (GastoEntity gastoEntity : gastos) {
			service.save(gastoEntity);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GastoEntity>> listarGastos() {
		String name = auth.getAuthentication().getName();
		System.out.println(String.format("Usuário logado: %s", name));
		return new ResponseEntity<List<GastoEntity>>(service.listAllByName(name), HttpStatus.OK);
	}

}
