package br.com.brunots.testes.everis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunots.testes.everis.entity.CategoriaEntity;
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
	public ResponseEntity<List<GastoEntity>> listarGastosComData(@PathParam("data") String data) {
		String name = auth.getAuthentication().getName();

		if (data == null) {
			return new ResponseEntity<List<GastoEntity>>(service.listAllByName(name), HttpStatus.OK);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date;
			try {
				date = sdf.parse(data);
			} catch (ParseException e) {
				System.out.println(String.format(
						"Não foi possivel fazer o parser da data '%s'. Assumindo a data de hoje para fazer a busca.",
						data));
				date = new Date();
			}
			return new ResponseEntity<List<GastoEntity>>(service.listAllByNameWithDate(name, date), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{gastoId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> incluirCategoria(@PathVariable("gastoId") String gastoId, @RequestBody CategoriaEntity categoria) {
		service.incluirCategoria(gastoId, categoria);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
