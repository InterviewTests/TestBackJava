package br.com.santander.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.entity.GastoEntity;

@RestController
public class GastoResource {

	private Map<Long, GastoEntity> gastos;

	public GastoResource() {
		gastos = new HashMap<Long, GastoEntity>();

		GastoEntity g1 = new GastoEntity("Mercado", new BigDecimal("500.53"), 123354851L, Calendar.getInstance());
		GastoEntity g2 = new GastoEntity("Shopping", new BigDecimal("1583.68"), 15565115L, Calendar.getInstance());

		gastos.put(g1.getCodigoUsuario(), g1);
		gastos.put(g2.getCodigoUsuario(), g2);
	}
	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public ResponseEntity<List<GastoEntity>> listar() {
		return new ResponseEntity<List<GastoEntity>>(new ArrayList<GastoEntity>(gastos.values()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gastos/{id}", method = RequestMethod.GET)
	public ResponseEntity<GastoEntity> buscar(@PathVariable("id") Long id) {
	  GastoEntity gasto = gastos.get(id);
	 
	  if (gasto == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	 
	  return new ResponseEntity<GastoEntity>(gasto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gastos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		GastoEntity curso = gastos.remove(id);
	 
	  if (curso == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	 
	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/gastos", method = RequestMethod.POST)
	public ResponseEntity<GastoEntity> update(@RequestBody GastoEntity gasto) {

	    if (gasto != null) {
	        gastos.put(gasto.getCodigoUsuario(), gasto);
	    }

	    return new ResponseEntity<GastoEntity>(gasto, HttpStatus.OK);
	}
}
